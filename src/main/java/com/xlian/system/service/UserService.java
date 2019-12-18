package com.xlian.system.service;

import com.xlian.system.dto.UserDto;
import com.xlian.system.model.User;

import java.util.List;

public interface UserService {

	User findById(Integer id);

	List<User> findByCondition(UserDto userDto);

	void save(UserDto userDto);

	void update(UserDto userDto);

	void deleteById(Integer id);

	/**
	 * 根据用户名查询
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
}
