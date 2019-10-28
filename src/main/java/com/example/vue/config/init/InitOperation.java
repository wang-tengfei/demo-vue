package com.example.vue.config.init;

import com.example.vue.biz.menu.domain.Menu;
import com.example.vue.biz.menu.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 17:25 2019-10-28
 * @modified by:
 */
@Component
@Slf4j
public class InitOperation implements CommandLineRunner {

    @Autowired
    private MenuService menuService;

    @Override
    public void run(String... args) throws Exception {
//        initMenu();
    }

    private void initMenu() {
        ArrayList<Menu> menus = new ArrayList<>();
        Menu menu1 = new Menu("用户管理", "", "用户信息管理", null);
        menus.add(menu1);
        Menu menu2 = new Menu("角色管理", "", "角色信息管理", null);
        menus.add(menu2);
        Menu menu3 = new Menu("权限管理", "", "权限信息管理", null);
        menus.add(menu3);
        menus.forEach(w -> menuService.addMenu(w));
    }
}
