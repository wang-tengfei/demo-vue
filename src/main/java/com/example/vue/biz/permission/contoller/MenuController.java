package com.example.vue.biz.permission.contoller;

import com.example.vue.common.constant.Result;
import com.example.vue.biz.permission.domain.Permission;
import com.example.vue.biz.permission.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 15:43 2019-10-28
 * @modified by:
 */
@RestController
@RequestMapping("/vue")
@Validated
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    public Result addMenu(@Validated(value = Permission.AddValid.class) @RequestBody Permission menu) {
        return menuService.addMenu(menu);
    }

    @RequestMapping(value = "/menu/{menu-id}", method = RequestMethod.PUT)
    public Result updateMenu(@Validated(value = Permission.EditValid.class) @PathVariable("menu-id") String menuId, @RequestBody Permission menu) {
        menu.setId(menuId);
        return menuService.updateMenu(menu);
    }

    @RequestMapping(value = "/menu/{menu-id}", method = RequestMethod.DELETE)
    public Result deleteMenu(@PathVariable("menu-id") String menuId, Boolean deleteChild) {
        return menuService.deleteMenu(menuId, deleteChild);
    }

    @RequestMapping(value = "/menu/{menu-id}", method = RequestMethod.GET)
    public Result getMenuById(@PathVariable("menu-id") String menuId) {
        return menuService.getMenuById(menuId);
    }

    @RequestMapping(value = "/menu/parent", method = RequestMethod.GET)
    public Result getMenuByParentId(@RequestParam(value = "parentId", required = false) String parentId) {
        return menuService.getMenuByParentId(parentId);
    }
}
