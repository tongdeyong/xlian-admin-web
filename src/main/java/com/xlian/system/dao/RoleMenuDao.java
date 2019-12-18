package com.xlian.system.dao;

import com.xlian.system.model.RoleMenu;

import java.util.List;

public interface RoleMenuDao {

	RoleMenu findById(Integer id);

	List<RoleMenu> findByCondition(RoleMenu roleMenu);

	void save(RoleMenu roleMenu);

	int update(RoleMenu roleMenu);

	int deleteById(Integer id);

	void deleteByRoleId(Integer roleId);
}
