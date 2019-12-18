package com.xlian.system.controller;

import com.github.pagehelper.Page;
import com.xlian.common.vo.Result;
import com.xlian.system.vo.MenuVO;
import com.xlian.system.model.Menu;
import com.xlian.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public Result getMenuPageList(MenuVO menuVO) {
        List<MenuVO> menuList = menuService.findByCondition(menuVO);
        if (menuList instanceof Page) {
            Page page = (Page) menuList;
            return Result.ok(menuList, page.getPageNum(), page.getPageSize(), (int) page.getTotal());
        }
        return Result.ok(menuList);
    }

    @GetMapping("/all-list")
    public Result getMenuAllList(MenuVO menuVO) {
        List<MenuVO> menuList = menuService.findAll(menuVO);
        return Result.ok(menuList);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public Result save(@RequestBody Menu menu) {
        menuService.save(menu);
        return Result.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public Result update(@RequestBody Menu menu) {
        menuService.update(menu);
        return Result.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{menuId}")
    public Result remove(@PathVariable Integer menuId) {
        menuService.deleteById(menuId);
        return Result.ok();
    }
}
