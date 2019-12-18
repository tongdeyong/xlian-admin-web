package com.xlian.system.service;


import com.xlian.system.dto.DeptDto;
import com.xlian.system.model.Dept;

import java.util.List;


public interface DeptService {

	Dept findById(Integer id);

	List<DeptDto> findByCondition(DeptDto deptDto);

	void save(Dept dept);

	void update(Dept dept);

	void deleteById(Integer id);

    List<DeptDto> findAll(DeptDto deptDto);
}
