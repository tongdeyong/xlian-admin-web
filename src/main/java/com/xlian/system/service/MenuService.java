package com.xlian.system.service;

import com.xlian.system.dto.MenuDto;
import com.xlian.system.model.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {

	Menu findById(Integer id);

	List<MenuDto> findByCondition(MenuDto menuDto);

	void save(Menu user);

	void update(Menu user);

	void deleteById(Integer id);

    List<MenuDto> findAll(MenuDto menuDto);
}
