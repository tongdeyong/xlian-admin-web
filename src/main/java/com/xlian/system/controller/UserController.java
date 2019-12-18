package com.xlian.system.controller;

import com.github.pagehelper.Page;
import com.xlian.common.dto.Result;
import com.xlian.system.handler.UserHandler;
import com.xlian.common.utils.MD5Utils;
import com.xlian.system.dto.UserDto;
import com.xlian.system.model.User;
import com.xlian.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/system/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Result getUserPageList(UserDto userDto) {
        // 查询列表数据
        List<User> userList = userService.findByCondition(userDto);
        if (userList instanceof Page) {
            Page page = (Page) userList;
            return Result.ok(userList, page.getPageNum(), page.getPageSize(), (int) page.getTotal());
        }
        return Result.ok(userList, 0, 0, 0);
    }

    @GetMapping("/info")
    public Result getUserInfo() {
        return Result.ok(UserHandler.getCurrentUser());
    }

    @PostMapping("/save")
    public Result save(@RequestBody UserDto userDto) {
        userDto.setPassword(MD5Utils.encrypt(userDto.getUsername(), userDto.getPassword()));
        userService.save(userDto);
        return Result.ok();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody UserDto userDto) {
        userService.update(userDto);
        return Result.ok();
    }


    @PostMapping("/delete/{userId}")
    @ResponseBody
    public Result remove(@PathVariable Integer userId) {
        userService.deleteById(userId);
        return Result.ok();
    }

    @PostMapping("/resetPassword")
    @ResponseBody
    public Result resetPassword(@RequestBody UserDto userDto) {
        userService.update(userDto);
        return Result.ok();
    }

}
