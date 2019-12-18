package com.xlian.system.service;

import com.xlian.system.vo.UserVO;
import com.xlian.system.model.User;

import java.util.List;

public interface UserService {

	User findById(Integer id);

	List<User> findByCondition(UserVO userVO);

	void save(UserVO userVO);

	void update(UserVO userVO);

	void deleteById(Integer id);

	/**
	 * 根据用户名查询
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
}
