package com.example.vue.oauth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.vue.common.DesEncrypt;
import com.example.vue.common.ResultUtil;
import com.example.vue.common.constant.Result;
import com.example.vue.common.constant.ResultEnum;
import com.example.vue.common.exception.CustomerException;
import com.example.vue.oauth.repository.UserEntity;
import com.example.vue.user.modle.UserInfo;
import com.example.vue.user.repository.UserInfoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 14:44 2019-06-24
 * @modified by:
 */
@Service
@Slf4j
public class OauthServiceImpl implements OauthService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserInfoRepository userInfoRepository;

    private static final String SECRET = "123ius1yhs1d1bj2gas4si123eg";
    private static final String ISSUER = "Service";
    private static final String TOKEN_PREFIX = "token-";

    @Override
    public String getToken(UserInfo user) {
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        Date date = new Date();
        Date expireDate = getAfterDate(date, 0, 0, 0, 2, 0, 0);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        try {
            String token = JWT.create()
                    //签名头 header
                    .withHeader(map)
                    // 载荷信息 Payload
                    //.withClaim("loginName", user.getUserName())
                    // 签名的观众，即谁接受签名
                    .withAudience(DesEncrypt.encrypt(user.getId()))
                    // 签名生成者
                    .withIssuer(ISSUER)
                    // 签名主题
                    .withSubject("user token")
                    // 签名生成时间
                    .withIssuedAt(new Date())
                    // token过期时间
                    .withExpiresAt(expireDate)
                    // 签名
                    .sign(algorithm);
            log.info("generate token success");
            stringRedisTemplate.opsForValue().set(TOKEN_PREFIX + user.getId(), token, 2L, TimeUnit.HOURS);
            return token;
        } catch (IllegalArgumentException | JWTCreationException e) {
            log.error("create token error: {}", e.getMessage());
            return null;
        }
    }


    @Override
    public boolean verifyToken(String token) throws CustomerException {

        if (StringUtils.isEmpty(token)) {
            throw new CustomerException(ResultEnum.TOKEN_IS_EMPTY);
        }
        if (!token.contains(".")) {
            log.error("token 格式错误");
            throw new CustomerException(ResultEnum.TOKEN_INVALID);
        }

        String[] split = token.split("\\.");
        String claimEncoder = split[1];
//        BASE64Decoder decoder = new BASE64Decoder();
        Base64.Decoder decoder = Base64.getMimeDecoder();
        try {
//            byte[] bytes = decoder.decodeBuffer(claimEncoder);
            byte[] bytes = decoder.decode(claimEncoder);
            String claimDecoder = new String(bytes, StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(claimDecoder);
            JsonNode aud = jsonNode.path("aud");
            if (aud.isMissingNode()) {
                log.error("Audience is missing");
                throw new CustomerException(ResultEnum.TOKEN_INVALID);
            }
            String uid = DesEncrypt.decrypt(aud.asText());

            String userToken = stringRedisTemplate.opsForValue().get(TOKEN_PREFIX + uid);
            // 如果redis没有，直接使用jwt验证
            if (StringUtils.isEmpty(userToken)) {
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).withIssuer(ISSUER).build();

                DecodedJWT jwt = jwtVerifier.verify(token);
                String userId = jwt.getAudience().get(0);

                Optional<UserInfo> userInfo = userInfoRepository.findById(userId);
                if (!userInfo.isPresent()) {
                    log.error("user {}, not found", userId);
                    throw new CustomerException(ResultEnum.TOKEN_INVALID);
                }
            }
            if (!token.equals(userToken)) {
                log.error("token 信息不一致");
                throw new CustomerException(ResultEnum.TOKEN_INVALID);
            }
        }catch (TokenExpiredException e1) {
            throw new CustomerException(ResultEnum.TOKEN_EXPIRED);
        } catch (JWTVerificationException e2) {
            log.error("valid token error: {}", e2.getMessage());
            throw new CustomerException(ResultEnum.TOKEN_INVALID);
        } catch (IOException e3) {
            log.error("valid token error: {}", e3.getMessage());
            throw new CustomerException(ResultEnum.SERVER_ERROR);
        }

        return true;
    }

    @Override
    public Result getUserToken(String userId) {
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findById(userId);
        if (!optionalUserInfo.isPresent()) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        UserInfo user = optionalUserInfo.get();
        String token = getToken(user);
        UserEntity userEntity = new UserEntity(user.getId(), user.getUserName(), token, 7200L);
        return ResultUtil.success(userEntity);
    }

    /**
     * @param date   开始计时的时间
     * @param year   增加的年
     * @param month  增加的月
     * @param day    增加的日
     * @param hour   增加的小时
     * @param minute 增加的分钟
     * @param second 增加的秒
     * @return         
     */
    private Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second) {
        if (date == null) {
            date = new Date();
        }

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        if (year != 0) {
            calendar.add(Calendar.YEAR, year);
        }
        if (month != 0) {
            calendar.add(Calendar.MONTH, month);
        }
        if (day != 0) {
            calendar.add(Calendar.DATE, day);
        }
        if (hour != 0) {
            calendar.add(Calendar.HOUR_OF_DAY, hour);
        }
        if (minute != 0) {
            calendar.add(Calendar.MINUTE, minute);
        }
        if (second != 0) {
            calendar.add(Calendar.SECOND, second);
        }

        return calendar.getTime();
    }

    public static void main(String[] args) throws IOException {
        String s = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIwOTAyMzExNzdjNTk1OTI4MTQyMGEyY2FmMjI0MmI2MjQ1NjIyNjU2ZTBiY2Y4NTNhMmU3ZmQ4YzdiN2UyYzc4ODhlYWY0Y2ViMDBjOWJlMyIsInN1YiI6InVzZXIgdG9rZW4iLCJpc3MiOiJTZXJ2aWNlIiwiZXhwIjoxNTYxNDM1Nzc1LCJpYXQiOjE1NjE0Mjg1NzV9.Z3FJofcH16CVyXtXzEFg4Gjn634Puni37CQMqkJV6uM";
        String[] split = s.split("\\.");
//        BASE64Decoder decoder = new BASE64Decoder();
        Base64.Decoder decoder = Base64.getMimeDecoder();
        byte[] bytes = decoder.decode(split[1]);
        String claimDecoder = new String(bytes, StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(claimDecoder);
        JsonNode aud = jsonNode.path("aud");
        System.out.println(DesEncrypt.decrypt(aud.asText()));
    }
}
