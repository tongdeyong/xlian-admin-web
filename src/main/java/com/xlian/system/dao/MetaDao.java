package com.xlian.system.dao;

import com.xlian.system.model.Column;
import com.xlian.system.model.Table;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface MetaDao {
	@Select("select table_name, engine, table_comment, create_time from information_schema.tables"
			+ " where table_schema = (select database())")
	List<Table> listTable();


	@Select("select column_name, data_type columnType, column_comment from information_schema.columns"
			+ " where table_name = #{tableName} and table_schema = (select database()) order by ordinal_position")
	List<Column> listColumn(String tableName);
}
