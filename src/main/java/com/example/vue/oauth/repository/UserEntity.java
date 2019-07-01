package com.example.vue.oauth.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 18:01 2019-06-24
 * @modified by:
 */
@Data
@AllArgsConstructor
public class UserEntity {

    private String userId;

    private String userName;

    private String token;

    private Long expiresIn;

}
