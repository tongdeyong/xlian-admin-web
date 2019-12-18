package com.xlian.system.dao;

import com.xlian.system.dto.MenuDto;
import com.xlian.system.model.Menu;

import java.util.List;

public interface MenuDao {

	Menu findById(Integer id);

	List<MenuDto> findByCondition(MenuDto menuDto);

	void save(Menu user);

	int update(Menu user);

	int deleteById(Integer id);
}
