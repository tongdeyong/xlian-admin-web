package com.xlian.system.controller;

import com.github.pagehelper.Page;
import com.xlian.common.vo.Result;
import com.xlian.system.model.Column;
import com.xlian.system.model.Table;
import com.xlian.system.service.MetaService;
import com.xlian.system.vo.ColumnVO;
import com.xlian.system.vo.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/system/meta")
public class MetaController {

    @Autowired
    private MetaService metaService;

    @GetMapping("/table/list")
    public Result tableList(TableVO tableVO) {
        try {
            List<Table> tableList = metaService.listTable(tableVO);
            if (tableList instanceof Page) {
                Page page = (Page) tableList;
                return Result.ok(tableList, page.getPageNum(), page.getPageSize(), (int) page.getTotal());
            }
            return Result.ok(tableList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }

    }

    @GetMapping("/column/list")
    public Result tableList(ColumnVO columnVO) {
        try {
            List<Column> columnList = metaService.listColumn(columnVO);
            if (columnList instanceof Page) {
                Page page = (Page) columnList;
                return Result.ok(columnList, page.getPageNum(), page.getPageSize(), (int) page.getTotal());
            }
            return Result.ok(columnList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }

    }

}
