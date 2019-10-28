package com.example.vue.biz.permission.service;

import com.example.vue.common.constant.Result;
import com.example.vue.biz.permission.domain.Permission;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 14:14 2019-10-28
 * @modified by:
 */
public interface MenuService {

    Result addMenu(Permission menu);

    Result updateMenu(Permission menu);

    Result deleteMenu(String id, boolean deleteChild);

    Result getMenuById(String id);

    Result getMenuTree();

    Result getMenuByParentId(String parentId);

}
