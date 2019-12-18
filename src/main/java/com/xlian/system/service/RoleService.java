package com.xlian.system.service;

import com.xlian.system.dto.RoleDto;
import com.xlian.system.model.Role;
import com.xlian.system.model.RoleMenu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

	Role findById(Integer id);

	List<Role> findByCondition(RoleDto roleDto);

	void save(Role user);

	void update(Role user);

	void deleteById(Integer id);

    List<RoleMenu> getRoleMenu(RoleMenu roleMenu);

	void addMenuPermission(RoleDto roleDto);
}
