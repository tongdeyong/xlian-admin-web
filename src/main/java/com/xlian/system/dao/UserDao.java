package com.xlian.system.dao;

import com.xlian.system.vo.UserVO;
import com.xlian.system.model.User;

import java.util.List;


public interface UserDao {

	User findById(Integer id);

	List<User> findByCondition(UserVO userVO);

	void save(User user);
	
	int update(User user);
	
	int deleteById(Integer id);
}
