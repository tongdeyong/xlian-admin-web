package com.xlian.system.dao;

import com.xlian.system.vo.DeptVO;
import com.xlian.system.model.Dept;

import java.util.List;

public interface DeptDao {

	Dept findById(Integer id);

	List<DeptVO> findByCondition(DeptVO deptVO);

	void save(Dept user);

	int update(Dept user);

	int deleteById(Integer id);
}
