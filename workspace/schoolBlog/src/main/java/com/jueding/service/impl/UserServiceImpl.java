package com.jueding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jueding.common.dto.LoginDto;
import com.jueding.entity.User;
import com.jueding.mapper.UserMapper;
import com.jueding.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
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
    public Page<User> getPage(int currentPage, int pageSize, User user) {
        Page<User> page = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(user.getUName()), User::getUName, user.getUName());
        return userMapper.selectPage(page, lqw);
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
    public List<User> findUserByEmail(String email) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("u_email", email);
        return userMapper.selectList(userQueryWrapper);
    }

    @Override
    public boolean delUserById(int id) {
        int i = userMapper.deleteById(id);
        return i != 0;
    }

    @Override
    public boolean updatePwdByEmail(LoginDto loginDto) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("u_email", loginDto.getEmail());
        User user = userMapper.selectOne(userQueryWrapper);
        user.setUPassword(DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes(StandardCharsets.UTF_8)));
        int i = userMapper.updateById(user);
        return i > 0;
    }
}
