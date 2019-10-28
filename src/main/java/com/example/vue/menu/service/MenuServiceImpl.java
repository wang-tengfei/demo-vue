package com.example.vue.menu.service;

import com.example.vue.common.ResultUtil;
import com.example.vue.common.constant.Result;
import com.example.vue.common.constant.ResultEnum;
import com.example.vue.common.constant.VueConstant;
import com.example.vue.menu.domain.Menu;
import com.example.vue.menu.repository.MenuRepository;
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
    public Result addMenu(Menu menu) {
        String parentId = menu.getParentId();
        if (!StringUtils.isEmpty(parentId)) {
            Optional<Menu> menuOptional = menuRepository.findById(parentId);
            if (!menuOptional.isPresent()) {
                log.error("menu parent id {} is not exist", parentId);
                return ResultUtil.error(ResultEnum.INVALID_PARENT_ID);
            }
            menu.setParentId(parentId);
        }
        List<Menu> menuList = menuRepository.findByMenuName(menu.getMenuName());
        if (menuList.size() > 0) {
            return ResultUtil.error(ResultEnum.REPEATE_NAME);
        }
        menu.setId(UUID.randomUUID().toString());
        menu.setStatus(VueConstant.STATUS_NORMAL);
        menu.setCreateTime(System.currentTimeMillis());
        Menu save = menuRepository.save(menu);
        return ResultUtil.success(save);
    }

    @Override
    public Result updateMenu(Menu menu) {
        String id = menu.getId();
        Optional<Menu> menuOptional = menuRepository.findById(id);
        if (!menuOptional.isPresent()) {
            log.error("menu id {} is not exist", id);
            return ResultUtil.error(ResultEnum.INVALID_ID);
        }

        Menu menuDb = menuOptional.get();

        List<Menu> menuList = menuRepository.findByMenuName(menu.getMenuName());
        if (menuList.size() > 0 && !menuList.get(0).getId().equals(menuDb.getId())) {
            return ResultUtil.error(ResultEnum.REPEATE_NAME);
        }

        String parentId = menu.getParentId();
        if (!StringUtils.isEmpty(parentId)) {
            Optional<Menu> parMenuOptional = menuRepository.findById(parentId);
            if (!parMenuOptional.isPresent()) {
                log.error("menu parent id {} is not exist", parentId);
                return ResultUtil.error(ResultEnum.INVALID_PARENT_ID);
            }
            menuDb.setParentId(parentId);
        }
        menuDb.setMenuName(menu.getMenuName());
        menuDb.setDescription(menu.getDescription());
        menuDb.setUpdateTime(System.currentTimeMillis());
        Menu save = menuRepository.save(menuDb);
        return ResultUtil.success(save);
    }

    @Override
    public Result deleteMenu(String id, boolean deleteChild) {
        Optional<Menu> menuOptional = menuRepository.findById(id);
        if (!menuOptional.isPresent()) {
            log.error("menu id {} is not exist", id);
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        Menu menu = menuOptional.get();
        if (!StringUtils.isEmpty(menu.getParentId())) {
            List<Menu> menuList = menuRepository.findByParentId(menu.getParentId());
            if (deleteChild && menuList.size() > 0) {
                menuList.forEach(w -> w.setStatus(VueConstant.STATUS_DELETE));
                menuRepository.saveAll(menuList);
            }
        }
        menu.setStatus(VueConstant.STATUS_DELETE);
        menuRepository.save(menu);
        return ResultUtil.success();
    }

    @Override
    public Result getMenuById(String id) {
        Optional<Menu> menuOptional = menuRepository.findById(id);
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
        List<Menu> menuList;
        if (parentId == null) {
            menuList = menuRepository.findAll();
        } else {
            menuList = menuRepository.findByParentId(parentId);
        }
        List<Menu> parentMenu = menuList.stream().filter(w -> w.getParentId() != null).collect(Collectors.toList());
        ArrayList<Map<String, Object>> treeList = new ArrayList<>();
        for (Menu menu : parentMenu) {
            HashMap<String, Object> map = new HashMap<>(parentMenu.size());
            map.put("id", menu.getId());
            map.put("label", menu.getMenuName());
            treeList.add(map);
        }
        return ResultUtil.success(treeList);
    }
}
