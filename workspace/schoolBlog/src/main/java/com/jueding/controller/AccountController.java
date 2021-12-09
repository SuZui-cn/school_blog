package com.jueding.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jueding.common.dto.LoginDto;
import com.jueding.common.lang.Result;
import com.jueding.entity.User;
import com.jueding.service.UserService;
import com.jueding.util.EmailUtil;
import com.jueding.util.JwtUtils;
import com.jueding.util.StringUtils;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.List;

/**
 * @author 北落燕门
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("u_name", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");
        if (!user.getUPassword().equals(DigestUtils.md5DigestAsHex(loginDto.getPassword()
                .getBytes(StandardCharsets.UTF_8)))) {
            return Result.error("密码不正确");
        }
        String jwt = jwtUtils.generateToken(user.getUId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.success(MapUtil.builder()
                .put("id", user.getUId())
                .put("username", user.getUName())
                .put("sex", user.getUSex())
                .put("phone", user.getUPhone())
                .put("email", user.getUEmail())
                .map()
        );
    }


    @GetMapping("/emailLogin")
    public Result emailLogin(@RequestParam("email") String email) throws MessagingException, javax.mail.MessagingException, GeneralSecurityException {
        String randomString = StringUtils.getRandomString(6);
        EmailUtil.sendEmail(email, randomString);
        log.info(randomString);
        return Result.success("邮件发送成功", null);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        List<User> users = userService.findUserByName(user.getUName());
        if (users.size() > 0) {
            return Result.error("注册失败，用户名已注册");
        } else {
            return userService.save(user) ? Result.success("注册成功") : Result.error();
        }
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }

}
