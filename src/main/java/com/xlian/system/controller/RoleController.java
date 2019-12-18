package com.xlian.system.controller;

import com.github.pagehelper.Page;
import com.xlian.common.vo.Result;
import com.xlian.system.vo.RoleVO;
import com.xlian.system.model.Role;
import com.xlian.system.model.RoleMenu;
import com.xlian.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public Result getRolePageList(RoleVO roleVO) {
        List<Role> roleList = roleService.findByCondition(roleVO);
        if (roleList instanceof Page) {
            Page page = (Page) roleList;
            return Result.ok(roleList, page.getPageNum(), page.getPageSize(), (int) page.getTotal());
        }
        return Result.ok(roleList, 0, 0, 0);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public Result save(@RequestBody Role role) {
        roleService.save(role);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Role role) {
        roleService.update(role);
        return Result.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{roleId}")
    public Result remove(@PathVariable Integer roleId) {
        roleService.deleteById(roleId);
        return Result.ok();
    }

    /**
     * 获取已有的菜单权限
     * @param roleMenu
     * @return
     */
    @GetMapping("/role-menu")
    public Result getRoleMenu(RoleMenu roleMenu) {
        List<RoleMenu> roleMenuList = roleService.getRoleMenu(roleMenu);
        List<Integer> result = new ArrayList<>();
        roleMenuList.forEach(item -> result.add(item.getMenuId()));
        return Result.ok(result);
    }

    /**
     * 增加
     * @param roleVO
     * @return
     */
    @PostMapping("/add-menu-permission")
    public Result addMenuPermission(@RequestBody RoleVO roleVO) {
        roleService.addMenuPermission(roleVO);
        return Result.ok();
    }


}
