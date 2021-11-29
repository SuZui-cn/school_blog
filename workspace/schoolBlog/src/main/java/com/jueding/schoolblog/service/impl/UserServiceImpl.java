package com.jueding.schoolblog.service.impl;

import com.jueding.schoolblog.entity.User;
import com.jueding.schoolblog.mapper.UserMapper;
import com.jueding.schoolblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 北落燕门
 * @since 2021-11-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
