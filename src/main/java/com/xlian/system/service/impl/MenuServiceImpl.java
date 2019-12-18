package com.xlian.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.xlian.system.dao.MenuDao;
import com.xlian.system.dto.MenuDto;
import com.xlian.system.model.Menu;
import com.xlian.system.service.MenuService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public Menu findById(Integer id) {
        return menuDao.findById(id);
    }

    @Override
    public List<MenuDto> findByCondition(MenuDto menuDto) {
        PageHelper.startPage(menuDto.getPageNum(), menuDto.getPageSize());
        List<MenuDto> menuList = menuDao.findByCondition(menuDto);
        //查询子节点
        buildTreeData(menuList);
        return menuList;
    }

    private void buildTreeData(List<MenuDto> menuList){
        Stack<MenuDto> stack = new Stack<>();
        stack.addAll(menuList);
        while (!stack.empty()) {
            MenuDto pop = stack.pop();
            MenuDto temp = new MenuDto();
            temp.setParentId(pop.getId());
            List<MenuDto> children = menuDao.findByCondition(temp);
            if (CollectionUtils.isNotEmpty(children)) {
                pop.setChildren(children);
                children.forEach(stack::push);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Menu menu) {
        menuDao.save(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Menu menu) {
        menu.setUpdateTime(new Date());
        menuDao.update(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        menuDao.deleteById(id);
        //递归删除子节点
        MenuDto menuDto = new MenuDto();
        menuDto.setParentId(id);
        List<MenuDto> menuDtoList = menuDao.findByCondition(menuDto);
        Stack<MenuDto> stack = new Stack<>();
        stack.addAll(menuDtoList);
        while (!stack.empty()) {
            MenuDto pop = stack.pop();
            menuDao.deleteById(pop.getId());
            menuDto.setParentId(pop.getId());
            List<MenuDto> temp = menuDao.findByCondition(menuDto);
            if (CollectionUtils.isNotEmpty(temp)) {
                temp.forEach(stack::push);
            }
        }
    }

    @Override
    public List<MenuDto> findAll(MenuDto menuDto) {
        List<MenuDto> menuList = menuDao.findByCondition(menuDto);
        //查询子节点
        buildTreeData(menuList);
        return menuList;
    }

}
