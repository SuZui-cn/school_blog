package com.jueding.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jueding.common.Result;
import com.jueding.entity.User;
import com.jueding.service.ArticleService;
import com.jueding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 北落燕门
 * @since 2021-11-29
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;

    /**
     * 查看所有用户的接口
     *
     * @return 通用返回集
     */
    @GetMapping("/getAll")
    public Result getAll() {
        ArrayList<User> users = (ArrayList<User>) userService.getAll();
        return users.size() == 0 ? Result.error("查询失败") : Result.success(users);
    }

    @GetMapping("/getOne/{uid}")
    public Result getOne(@PathVariable("uid") int uid) {
        List<User> users = userService.findUserById(uid);
        return users.size() != 0 ? Result.success(users) : Result.error();
    }

    /**
     * 分页查询接口
     *
     * @param currentPage 当前页数
     * @param pageSize    每页大小
     * @return 通用返回集
     */
    @GetMapping("/getPage/{currentPage}/{pageSize}")
    public Result getPage(@PathVariable("currentPage") int currentPage,
                          @PathVariable("pageSize") int pageSize) {
        Page<User> page = userService.getPage(currentPage, pageSize);
        //请求页数超过总页数时重新查询
        if (currentPage > page.getPages()) {
            page = userService.getPage((int) page.getPages(), pageSize);
        }
        return page.getTotal() != 0 ? Result.success(page) : Result.error("查询失败");
    }

    /**
     * 添加用户接口
     *
     * @param user 用户实体
     * @return 通用返回集
     */
    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user) {
        List<User> userByName = userService.findUserByName(user.getUName());
        if (userByName.size() == 0) {
            return Result.error();
        }
        return userService.save(user) ? Result.success() : Result.error();
    }

    /**
     * 删除用户接口
     *
     * @param id 用户id
     * @return 通用返回集
     */
    @DeleteMapping("{id}")
    public Result delete(@PathVariable("id") int id) {
        return userService.delUserById(id) ? Result.success() : Result.error();
    }

    /**
     * 更新用户接口
     *
     * @param user 用户对象
     * @return 通用返回集
     */
    @PutMapping
    public Result update(@Validated @RequestBody User user) {
        boolean flag = userService.updateById(user);
        return flag ? Result.success() : Result.error();
    }
}
