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

    @Query(value = "{status: 1}", fields = "{password: 0}")
    List<UserInfo> getAllUsers();


//    @Query(value = "{status: ?1, user_name : ?0}", fields = "{password: 0}")
//    List<UserInfo> getAllUsersWithPage(Pageable pageable, String username, Integer status);

    @CountQuery(value = "{status: {'$in' : ?1}, user_name : {'$in' : ?0}}")
    Long getUserCount(String[] username, Integer[] status);

    /**
     * login
     * @param userName
     * @param password
     * @return
     */
    Optional<UserInfo> getUserInfoByUserNameAndPassword(String userName, String password);
}
