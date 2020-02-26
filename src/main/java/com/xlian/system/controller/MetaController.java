package com.xlian.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.xlian.common.utils.PageUtils;
import com.xlian.common.vo.Result;
import com.xlian.system.model.Column;
import com.xlian.system.model.Table;
import com.xlian.system.service.MetaService;
import com.xlian.system.vo.ColumnVO;
import com.xlian.system.vo.SqlQueryVO;
import com.xlian.system.vo.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/query")
    public Result query(@RequestBody SqlQueryVO queryVO) {
        try {
            if (StringUtils.isBlank(queryVO.getSql())) {
                return Result.error("请输入sql");
            }
            if (queryVO.getSql().contains("update") || queryVO.getSql().contains("insert") || queryVO.getSql().contains("delete")) {
                return Result.error("仅仅支持select语句");
            }
            List<Map<String, Object>> data = metaService.querySql(queryVO.getSql());
            return Result.ok(PageUtils.page(data, queryVO.getPageNum(), queryVO.getPageSize()), queryVO.getPageNum(), queryVO.getPageSize(), data.size());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/generateCode")
    public void generateSql(@RequestBody TableVO tableVO, HttpServletResponse response) {
        try {
            response.setCharacterEncoding("utf-8");
            ServletOutputStream outputStream = response.getOutputStream();
            if (CollectionUtils.isEmpty(tableVO.getTableNameList())) {
                response.setContentType("application/json");
                outputStream.write(JSON.toJSONString(Result.error("请选择表")).getBytes(StandardCharsets.UTF_8));
                outputStream.close();
                return;
            }
            ByteArrayOutputStream byteArrayOutputStream = metaService.generateSql(tableVO);
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=code.zip");
            outputStream.write(byteArrayOutputStream.toByteArray());
            outputStream.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
