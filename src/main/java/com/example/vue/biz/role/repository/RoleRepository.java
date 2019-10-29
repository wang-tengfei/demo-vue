package com.example.vue.biz.role.repository;

import com.example.vue.biz.role.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author tengfei
 */
public interface RoleRepository extends MongoRepository<Role, Long>, RoleRepositoryCustom {

    /**
     * get one by id
     * @param roleId
     * @return
     */
    @Query(value = "{status: 1, _id: ?0}")
    @Override
    Optional<Role> findById(Long roleId);

    /**
     * get one by role name
     * @param roleName
     * @return
     */
    @Query(value = "{status: 1, role_name: ?0}")
    List<Role> findByRoleName(String roleName);

    /**
     * get all role
     * @return
     */
    @Query(value = "{status: 1}")
    @Override
    List<Role> findAll();
}
