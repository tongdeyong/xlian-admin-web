package com.xlian.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.xlian.common.utils.BeanUtil;
import com.xlian.system.dao.RoleDao;
import com.xlian.system.dao.RoleMenuDao;
import com.xlian.system.dto.RoleDto;
import com.xlian.system.model.Role;
import com.xlian.system.model.RoleMenu;
import com.xlian.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public Role findById(Integer id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Role> findByCondition(RoleDto roleDto) {
        PageHelper.startPage(roleDto.getPageNum(), roleDto.getPageSize());
        Role role = BeanUtil.copyProperties(roleDto, Role.class);
        return roleDao.findByCondition(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        roleDao.deleteById(id);
    }

    @Override
    public List<RoleMenu> getRoleMenu(RoleMenu roleMenu) {
        return roleMenuDao.findByCondition(roleMenu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMenuPermission(RoleDto roleDto) {
        List<Integer> menuIdList = roleDto.getMenuIdList();
        if (roleDto.getRoleId() == null || menuIdList == null) {
            throw new RuntimeException("参数有误");
        }
        //删除已有的权限值
        roleMenuDao.deleteByRoleId(roleDto.getRoleId());
        //持久化记录
        menuIdList.forEach(item->{
            RoleMenu model = new RoleMenu();
            model.setRoleId(roleDto.getRoleId());
            model.setMenuId(item);
            roleMenuDao.save(model);
        });
    }

}
