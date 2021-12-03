package com.jueding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jueding.entity.User;
import com.jueding.mapper.UserMapper;
import com.jueding.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 北落燕门
 * @since 2021-11-29
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userMapper.selectList(null);
    }

    @Override
    public Page<User> getPage(int currentPage, int pageSize) {
        Page<User> page = new Page<>(currentPage, pageSize);
        return userMapper.selectPage(page, null);
    }

    @Override
    public List<User> findUserByName(String name) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("u_name", name);
        return userMapper.selectList(userQueryWrapper);
    }

    @Override
    public List<User> findUserById(int id) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("u_id", id);
        return userMapper.selectList(userQueryWrapper);
    }

    @Override
    public boolean delUserById(int id) {
        int i = userMapper.deleteById(id);
        return i != 0;
    }
}
