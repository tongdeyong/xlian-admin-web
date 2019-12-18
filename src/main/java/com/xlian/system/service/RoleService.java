package com.xlian.system.service;

import com.xlian.system.vo.RoleVO;
import com.xlian.system.model.Role;
import com.xlian.system.model.RoleMenu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

	Role findById(Integer id);

	List<Role> findByCondition(RoleVO roleVO);

	void save(Role user);

	void update(Role user);

	void deleteById(Integer id);

    List<RoleMenu> getRoleMenu(RoleMenu roleMenu);

	void addMenuPermission(RoleVO roleVO);
}
