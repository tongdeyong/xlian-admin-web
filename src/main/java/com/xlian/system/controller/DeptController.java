package com.xlian.system.controller;

import com.github.pagehelper.Page;
import com.xlian.common.dto.Result;
import com.xlian.system.dto.DeptDto;
import com.xlian.system.model.Dept;
import com.xlian.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/list")
    public Result getDeptPageList(DeptDto deptDto) {
        List<DeptDto> deptList = deptService.findByCondition(deptDto);
        if (deptList instanceof Page) {
            Page page = (Page) deptList;
            return Result.ok(deptList, page.getPageNum(), page.getPageSize(), (int) page.getTotal());
        }
        return Result.ok(deptList);
    }
    @GetMapping("/all-list")
    public Result getDeptAllList(DeptDto deptDto) {
        List<DeptDto> deptList = deptService.findAll(deptDto);
        return Result.ok(deptList);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public Result save(@RequestBody Dept dept) {
        deptService.save(dept);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Dept dept) {
        deptService.update(dept);
        return Result.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{deptId}")
    public Result remove(@PathVariable Integer deptId) {
        deptService.deleteById(deptId);
        return Result.ok();
    }
}
