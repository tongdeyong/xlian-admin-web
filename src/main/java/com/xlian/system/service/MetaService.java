package com.xlian.system.service;

import com.xlian.system.model.Column;
import com.xlian.system.model.Table;
import com.xlian.system.vo.ColumnVO;
import com.xlian.system.vo.TableVO;

import java.util.List;
import java.util.Map;

public interface MetaService {

    List<Table> listTable(TableVO tableVO);

    List<Column> listColumn(ColumnVO columnVO);

    List<Map<String, Object>> querySql(String sql);

    void generateSql(TableVO tableVO);
}
