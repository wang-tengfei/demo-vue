package com.example.vue.menu.contoller;

import com.example.vue.common.constant.Result;
import com.example.vue.menu.domain.Menu;
import com.example.vue.menu.service.MenuService;
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
    public Result addMenu(@Validated(value = Menu.AddValid.class) @RequestBody Menu menu) {
        return menuService.addMenu(menu);
    }

    @RequestMapping(value = "/menu/{menu-id}", method = RequestMethod.PUT)
    public Result updateMenu(@Validated(value = Menu.EditValid.class) @PathVariable("menu-id") String menuId, @RequestBody Menu menu) {
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

    @RequestMapping(value = "/menu/parent/{parent-id}", method = RequestMethod.GET)
    public Result getMenuByParentId(@PathVariable("parent-id") String parentId) {
        return menuService.getMenuByParentId(parentId);
    }
}
