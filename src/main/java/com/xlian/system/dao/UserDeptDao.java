package com.xlian.system.dao;

import com.xlian.system.model.UserDept;


public interface UserDeptDao {

	UserDept findByUserId(Integer userId);

	void save(UserDept user);

	void update(UserDept user);

	void deleteById(Integer id);

	void deleteByUserId(Integer userId);
}
