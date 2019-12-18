package com.xlian.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.xlian.system.dao.DeptDao;
import com.xlian.system.dto.DeptDto;
import com.xlian.system.model.Dept;
import com.xlian.system.service.DeptService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Stack;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptDao deptDao;

	@Override
	public Dept findById(Integer id) {
		return deptDao.findById(id);
	}

	@Override
	public List<DeptDto> findByCondition(DeptDto deptDto) {
		PageHelper.startPage(deptDto.getPageNum(),deptDto.getPageSize());
		List<DeptDto> deptList = deptDao.findByCondition(deptDto);
		buildTreeData(deptList);
		return deptList;
	}

	private void buildTreeData(List<DeptDto> deptDtoList){
		Stack<DeptDto> stack = new Stack<>();
		stack.addAll(deptDtoList);
		while (!stack.empty()) {
			DeptDto pop = stack.pop();
			DeptDto temp = new DeptDto();
			temp.setParentId(pop.getId());
			List<DeptDto> children = deptDao.findByCondition(temp);
			if (CollectionUtils.isNotEmpty(children)) {
				pop.setChildren(children);
				children.forEach(stack::push);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(Dept dept) {
		deptDao.save(dept);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(Dept dept) {
		deptDao.update(dept);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) {
		deptDao.deleteById(id);
		//递归删除子节点
		DeptDto deptDto = new DeptDto();
		deptDto.setParentId(id);
		List<DeptDto> deptDtoList = deptDao.findByCondition(deptDto);
		Stack<DeptDto> stack = new Stack<>();
		stack.addAll(deptDtoList);
		while (!stack.empty()) {
			DeptDto pop = stack.pop();
			deptDao.deleteById(pop.getId());
			deptDto.setParentId(pop.getId());
			List<DeptDto> temp = deptDao.findByCondition(deptDto);
			if (CollectionUtils.isNotEmpty(temp)) {
				temp.forEach(stack::push);
			}
		}
	}

	@Override
	public List<DeptDto> findAll(DeptDto deptDto) {
		List<DeptDto> deptList = deptDao.findByCondition(deptDto);
		buildTreeData(deptList);
		return deptList;
	}

}
