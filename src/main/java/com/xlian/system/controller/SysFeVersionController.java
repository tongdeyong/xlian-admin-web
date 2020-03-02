package com.xlian.system.controller;

import com.github.pagehelper.Page;
import com.xlian.common.vo.Result;
import com.xlian.system.vo.SysFeVersionVO;
import com.xlian.system.model.SysFeVersion;
import com.xlian.system.service.SysFeVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/system/sysFeVersion")
public class SysFeVersionController {

    @Autowired
    private SysFeVersionService sysFeVersionService;

    @GetMapping("/list")
    public Result getPageList(SysFeVersionVO sysFeVersionVO) {
        try {
            List<SysFeVersion> sysFeVersionList = sysFeVersionService.findByCondition(sysFeVersionVO);
            if (sysFeVersionList instanceof Page) {
                Page page = (Page) sysFeVersionList;
                return Result.ok(sysFeVersionList, page.getPageNum(), page.getPageSize(), (int) page.getTotal());
            }
            return Result.ok(sysFeVersionList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/all-list")
    public Result getAllList(SysFeVersionVO sysFeVersionVO) {
        try {
            return Result.ok(sysFeVersionService.findAll(sysFeVersionVO));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/save")
    public Result save(@RequestBody SysFeVersion sysFeVersion) {
        try {
            sysFeVersionService.save(sysFeVersion);
            return Result.ok();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/update")
    public Result update(@RequestBody SysFeVersion sysFeVersion) {
        try {
            sysFeVersionService.update(sysFeVersion);
            return Result.ok();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public Result remove(@PathVariable Integer id) {
        try {
            sysFeVersionService.deleteById(id);
            return Result.ok();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }
}
