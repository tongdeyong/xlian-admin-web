package com.xlian.system.dao;

import com.xlian.system.dto.UserDto;
import com.xlian.system.model.User;

import java.util.List;


public interface UserDao {

	User findById(Integer id);

	List<User> findByCondition(UserDto userDto);

	void save(User user);
	
	int update(User user);
	
	int deleteById(Integer id);
}
