package com.xlian.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.xlian.common.utils.BeanUtil;
import com.xlian.system.dao.UserDao;
import com.xlian.system.dao.UserDeptDao;
import com.xlian.system.dto.UserDto;
import com.xlian.system.model.User;
import com.xlian.system.model.UserDept;
import com.xlian.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDeptDao userDeptDao;

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findByCondition(UserDto userDto) {
        PageHelper.startPage(userDto.getPageNum(), userDto.getPageSize());
        if (userDto.getDeptId().equals(0)) {
            userDto.setDeptId(null);
        }
        return userDao.findByCondition(userDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(UserDto userDto) {
        User user = BeanUtil.copyProperties(userDto, User.class);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userDao.save(user);

        UserDept userDept = new UserDept();
        userDept.setDeptId(userDept.getDeptId());
        userDept.setUserId(userDept.getUserId());
        userDeptDao.save(userDept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserDto userDto) {
        User user = BeanUtil.copyProperties(userDto, User.class);
        user.setUpdateTime(new Date());
        userDao.update(user);

        UserDept userDept = userDeptDao.findByUserId(user.getId());
        userDept.setDeptId(userDto.getDeptId());
        userDeptDao.update(userDept);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        userDao.deleteById(id);
        userDeptDao.deleteByUserId(id);
    }

    @Override
    public User findByUsername(String username) {
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        List<User> userList = userDao.findByCondition(userDto);
        if (CollectionUtils.isEmpty(userList)) {
            log.error("{}-用户不存在", username);
            throw new RuntimeException("用户不存在");
        }
        if (userList.size() > 1) {
            log.error("{}-查询到多个用户", username);
            throw new RuntimeException("查询到多个用户");
        }
        return userList.get(0);
    }


}
