package com.xlian.system.dao;

import com.xlian.system.model.UserRole;

import java.util.List;

public interface UserRoleDao {

	UserRole findById(Integer id);

	List<UserRole> findByCondition(UserRole user);

	void save(UserRole user);

	int update(UserRole user);

	int deleteById(Integer id);
}
