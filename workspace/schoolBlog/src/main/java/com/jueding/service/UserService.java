package com.jueding.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jueding.entity.User;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 北落燕门
 * @since 2021-11-29
 */
public interface UserService extends IService<User> {

    /**
     * 查询所有的用户
     *
     * @return 用户集合
     */
    List<User> getAll();

    /**
     * 分页查询
     *
     * @param currentPage 当前页数
     * @param pageSize    页数大小
     * @return 返回用户页对象
     */
    Page<User> getPage(int currentPage, int pageSize);

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 返回用户对象
     */
    List<User> findUserByName(String username);

    /**
     * 根据Id删除用户
     *
     * @param id 用户id
     * @return 是否成功
     */
    boolean delUserById(int id);

}
