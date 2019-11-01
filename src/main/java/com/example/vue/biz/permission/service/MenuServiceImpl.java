package com.example.vue.biz.permission.service;

import com.example.vue.common.ResultUtil;
import com.example.vue.common.constant.Result;
import com.example.vue.common.constant.ResultEnum;
import com.example.vue.common.constant.KeyConstant;
import com.example.vue.biz.permission.domain.Permission;
import com.example.vue.biz.permission.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 14:17 2019-10-28
 * @modified by:
 */
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Result addMenu(Permission menu) {
        String parentId = menu.getParentId();
        if (!StringUtils.isEmpty(parentId)) {
            Optional<Permission> menuOptional = menuRepository.findById(parentId);
            if (!menuOptional.isPresent()) {
                log.error("menu parent id {} is not exist", parentId);
                return ResultUtil.error(ResultEnum.INVALID_PARENT_ID);
            }
            menu.setParentId(parentId);
        }
        List<Permission> menuList = menuRepository.findByMenuName(menu.getPermissionName());
        if (menuList.size() > 0) {
            return ResultUtil.error(ResultEnum.REPEAT_NAME);
        }
        menu.setId(UUID.randomUUID().toString());
        menu.setStatus(KeyConstant.STATUS_NORMAL);
        menu.setCreateTime(System.currentTimeMillis());
        Permission save = menuRepository.save(menu);
        return ResultUtil.success(save);
    }

    @Override
    public Result updateMenu(Permission menu) {
        String id = menu.getId();
        Optional<Permission> menuOptional = menuRepository.findById(id);
        if (!menuOptional.isPresent()) {
            log.error("menu id {} is not exist", id);
            return ResultUtil.error(ResultEnum.INVALID_ID);
        }

        Permission menuDb = menuOptional.get();
        List<Permission> menuList = menuRepository.findByMenuName(menu.getPermissionName());
        if (menuList.size() > 0 && !menuList.get(0).getId().equals(menuDb.getId())) {
            return ResultUtil.error(ResultEnum.REPEAT_NAME);
        }

        String parentId = menu.getParentId();
        if (!StringUtils.isEmpty(parentId)) {
            Optional<Permission> parMenuOptional = menuRepository.findById(parentId);
            if (!parMenuOptional.isPresent()) {
                log.error("menu parent id {} is not exist", parentId);
                return ResultUtil.error(ResultEnum.INVALID_PARENT_ID);
            }
            menuDb.setParentId(parentId);
        }
        menuDb.setPath(menu.getPath());
        menuDb.setPermissionName(menu.getPermissionName());
        menuDb.setDescription(menu.getDescription());
        menuDb.setUpdateTime(System.currentTimeMillis());
        Permission save = menuRepository.save(menuDb);
        return ResultUtil.success(save);
    }

    @Override
    public Result deleteMenu(String id, boolean deleteChild) {
        Optional<Permission> menuOptional = menuRepository.findById(id);
        if (!menuOptional.isPresent()) {
            log.error("menu id {} is not exist", id);
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        Permission menu = menuOptional.get();
        if (!StringUtils.isEmpty(menu.getParentId())) {
            List<Permission> menuList = menuRepository.findByParentId(menu.getParentId());
            if (deleteChild && menuList.size() > 0) {
                menuList.forEach(w -> w.setStatus(KeyConstant.STATUS_DELETE));
                menuRepository.saveAll(menuList);
            }
        }
        menu.setStatus(KeyConstant.STATUS_DELETE);
        menuRepository.save(menu);
        return ResultUtil.success();
    }

    @Override
    public Result getMenuById(String id) {
        Optional<Permission> menuOptional = menuRepository.findById(id);
        if (!menuOptional.isPresent()) {
            log.error("menu id {} is not exist", id);
            return ResultUtil.error(ResultEnum.INVALID_ID);
        }
        return ResultUtil.success(menuOptional.get());
    }

    @Override
    public Result getMenuTree() {
        return null;
    }

    @Override
    public Result getMenuByParentId(String parentId) {
        List<Permission> menuList;
        if (parentId == null) {
            menuList = menuRepository.findAll();
        } else {
            menuList = menuRepository.findByParentId(parentId);
        }
        List<Permission> parentMenu = menuList.stream().filter(w -> w.getParentId() != null).collect(Collectors.toList());
        ArrayList<Map<String, Object>> treeList = new ArrayList<>();
        for (Permission menu : parentMenu) {
            HashMap<String, Object> map = new HashMap<>(parentMenu.size());
            map.put("id", menu.getId());
            map.put("label", menu.getPermissionName());
            treeList.add(map);
        }
        return ResultUtil.success(treeList);
    }
}
