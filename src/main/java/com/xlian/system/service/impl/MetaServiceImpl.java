package com.xlian.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.xlian.system.dao.MetaDao;
import com.xlian.system.model.Column;
import com.xlian.system.model.Table;
import com.xlian.system.service.MetaService;
import com.xlian.system.vo.ColumnVO;
import com.xlian.system.vo.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MetaServiceImpl implements MetaService {

    @Autowired
    private MetaDao metaDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
}
