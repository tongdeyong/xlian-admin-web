package com.xlian.system.dao;

import com.xlian.system.vo.MenuVO;
import com.xlian.system.model.Menu;

import java.util.List;

public interface MenuDao {

	Menu findById(Integer id);

	List<MenuVO> findByCondition(MenuVO menuVO);

	void save(Menu user);

	int update(Menu user);

	int deleteById(Integer id);
}
