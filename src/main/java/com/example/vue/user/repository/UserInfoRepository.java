package com.example.vue.user.repository;

import com.example.vue.user.modle.UserInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 11:45 2019-06-12
 * @modified by:
 */
public interface UserInfoRepository extends MongoRepository<UserInfo, String>, UserInfoRepositoryCustom {

    /**
     * get all users
     * @return
     */
    @Query(value = "{status: {$ne: 0}}", fields = "{password: 0}")
    List<UserInfo> getAllUsers();

    /**
     * get user count
     * @param username
     * @param status
     * @return
     */
    @CountQuery(value = "{ user_name : ?0}, status: {'$in' : ?1}")
    Long getUserCount(String username, Integer[] status);

    /**
     * login
     * @param userName
     * @param password
     * @return
     */
    @Query(value = "{status: {$ne: 0}, user_name: ?0, password: ?1}")
    Optional<UserInfo> getUserInfoByUserNameAndPassword(String userName, String password);
}
