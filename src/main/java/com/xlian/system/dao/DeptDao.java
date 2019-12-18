package com.xlian.system.dao;

import com.xlian.system.dto.DeptDto;
import com.xlian.system.model.Dept;

import java.util.List;

public interface DeptDao {

	Dept findById(Integer id);

	List<DeptDto> findByCondition(DeptDto deptDto);

	void save(Dept user);

	int update(Dept user);

	int deleteById(Integer id);
}
