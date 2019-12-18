package com.xlian.system.service;

import com.xlian.system.vo.MenuVO;
import com.xlian.system.model.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {

	Menu findById(Integer id);

	List<MenuVO> findByCondition(MenuVO menuVO);

	void save(Menu user);

	void update(Menu user);

	void deleteById(Integer id);

    List<MenuVO> findAll(MenuVO menuVO);
}
