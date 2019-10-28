package com.example.vue.menu.repository;

import com.example.vue.menu.domain.Menu;
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
public interface MenuRepository extends MongoRepository<Menu, String> {

    @Override
    @Query(value = "{status: {$ne: 0}, _id: ?0}")
    Optional<Menu> findById(String id);

    @Query(value = "{status: {$ne: 0}, menu_name: ?0}")
    List<Menu> findByMenuName(String name);

    @Override
    @Query("{status: {$ne: 0}}")
    List<Menu> findAll();

    @Query(value = "{status: {$ne: 0}, parent_id: ?0}")
    List<Menu> findByParentId(String parentId);
}
