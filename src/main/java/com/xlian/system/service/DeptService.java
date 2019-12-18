package com.xlian.system.service;


import com.xlian.system.vo.DeptVO;
import com.xlian.system.model.Dept;

import java.util.List;


public interface DeptService {

	Dept findById(Integer id);

	List<DeptVO> findByCondition(DeptVO deptVO);

	void save(Dept dept);

	void update(Dept dept);

	void deleteById(Integer id);

    List<DeptVO> findAll(DeptVO deptVO);
}
