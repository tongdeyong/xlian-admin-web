package com.xlian.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.xlian.common.enums.JavaTypeHandlerEnum;
import com.xlian.system.component.CodeGenComponent;
import com.xlian.system.dao.MetaDao;
import com.xlian.system.model.Column;
import com.xlian.system.model.Table;
import com.xlian.system.service.MetaService;
import com.xlian.system.vo.ColumnVO;
import com.xlian.system.vo.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MetaServiceImpl implements MetaService {

    @Autowired
    private MetaDao metaDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CodeGenComponent codeGenComponent;

    @Override
    public List<Table> listTable(TableVO tableVO) {
        PageHelper.startPage(tableVO.getPageNum(), tableVO.getPageSize());
        return metaDao.listTable();
    }

    @Override
    public List<Column> listColumn(ColumnVO columnVO) {
        PageHelper.startPage(columnVO.getPageNum(), columnVO.getPageSize());
        return metaDao.listColumn(columnVO.getTableName());
    }

    @Override
    public List<Map<String, Object>> querySql(String sql) {
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public ByteArrayOutputStream generateSql(TableVO tableVO) {
        List<String> tableNameList = tableVO.getTableNameList();
        String packageName = tableVO.getPackageName();
        String module = tableVO.getModule();
        List<Map<String, Object>> param = new ArrayList<>();
        tableNameList.forEach(table -> {
            Map<String, Object> data = new HashMap<>();
            if (tableVO.getRemoveTablePrefix()) {
                data.put("ClassName", firstCharToUpperCase(CaseUtils.toCamelCase(removeTablePrefix(table), false, '_')));
            } else {
                data.put("ClassName", firstCharToUpperCase(CaseUtils.toCamelCase(table, false, '_')));
            }
            data.put("tableName", table);
            if (tableVO.getRemoveTablePrefix()) {
                data.put("className", CaseUtils.toCamelCase(removeTablePrefix(table), false, '_'));
            } else {
                data.put("className", CaseUtils.toCamelCase(table, false, '_'));

            }
            data.put("module", module);
            data.put("packageName", packageName);
            data.put("primaryKeyType", "Integer");
            data.put("hasDate", false);
            List<Map<String, String>> columns = new ArrayList<>();

            List<Column> columnList = metaDao.listColumn(table);

            columnList.forEach(item -> {
                Map<String, String> column = new HashMap<>(5);
                column.put("attributeName", CaseUtils.toCamelCase(item.getColumnName(), false, '_'));
                column.put("name", item.getColumnName());
                column.put("comment", item.getColumnComment());
                JavaTypeHandlerEnum javaTypeHandlerEnum = JavaTypeHandlerEnum.getByJdbcType(item.getColumnType().toUpperCase());
                String javaType = javaTypeHandlerEnum.getJavaType();
                if (javaType.equals("Date")) {
                    data.put("hasDate", true);
                }
                if (item.getColumnKey().equals("PRI")) {
                    data.put("primaryKeyType", javaType);
                }
                column.put("type", javaType);
                columns.add(column);
            });
            data.put("columns", columns);
            param.add(data);
        });
        return codeGenComponent.generateCode(param, tableVO.getPackageName().replace(".", "/"), tableVO.getModule());
    }

    private static String removeTablePrefix(String tableName) {
        if (tableName.contains("_")) {
            return tableName.substring(tableName.indexOf("_") + 1);
        }
        return tableName;
    }

    private String firstCharToUpperCase(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        return new String(new char[]{str.charAt(0)}).toUpperCase() + str.substring(1);
    }

    public static void main(String[] args) {
        String s = CaseUtils.toCamelCase("user_name", false, '_');
        System.out.println("ss".substring(2));

    }


}
