package com.example.vue.biz.permission.repository;

import com.example.vue.biz.permission.domain.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 14:10 2019-10-28
 * @modified by:
 */
public interface MenuRepository extends MongoRepository<Permission, String> {

    @Override
    @Query(value = "{status: {$ne: 0}, _id: ?0}")
    Optional<Permission> findById(String id);

    @Query(value = "{status: {$ne: 0}, menu_name: ?0}")
    List<Permission> findByMenuName(String name);

    @Override
    @Query("{status: {$ne: 0}}")
    List<Permission> findAll();

    @Query(value = "{status: {$ne: 0}, parent_id: ?0}")
    List<Permission> findByParentId(String parentId);
}
