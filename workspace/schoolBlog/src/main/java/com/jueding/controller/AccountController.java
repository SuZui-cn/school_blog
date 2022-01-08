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
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
    public Result login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        System.out.println(loginDto);
        User user = userService.getOne(new QueryWrapper<User>().eq("u_name", loginDto.getUsername()));
//        Assert.notNull(user, "用户不存在");
        if (user == null) {
            return Result.error("用户不存在");
        } else if (!user.getUPassword().equals(DigestUtils.md5DigestAsHex(loginDto.getPassword()
                .getBytes(StandardCharsets.UTF_8)))) {
            return Result.error("密码不正确");
        }
        String jwt = jwtUtils.generateToken(user.getUId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");


        return Result.success("登录成功", MapUtil.builder()
                .put("id", user.getUId())
                .put("u_name", user.getUName())
                .put("u_sex", user.getUSex())
                .put("phone", user.getUPhone())
                .put("u_email", user.getUEmail())
                .map()
        );
    }


    @GetMapping("/emailLogin/{email}")
    public Result emailLogin(@PathVariable String email) throws MessagingException, javax.mail.MessagingException, GeneralSecurityException {
        log.info("接受邮件请求");
        String randomString = StringUtils.getRandomString(6);
        EmailUtil.sendEmail(email, randomString);
        log.info(randomString);
//        RedisTemplate<String, Object> redis = new RedisTemplate<>();
        Jedis jedis = new Jedis();
        jedis.set("code", randomString);
        log.info("将验证码存入缓存");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Jedis jedis1 = new Jedis();
                if (jedis1.get("code") != null) {
                    jedis1.del("code");
                }
                timer.cancel();
            }
        }, 1000 * 60);
//        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10);
//        executor.schedule(new Runnable() {
//            @Override
//            public void run() {
//                Jedis jedis1 = new Jedis();
//                if (jedis1.get("code") != null) {
//                    jedis1.del("code");
//                }
//            }
//        }, 1000 * 60, TimeUnit.SECONDS);
//        executor.shutdown();
        return Result.success("邮件发送成功", null);
    }

    @PostMapping("/checkCode")
    public Result checkCode(@RequestBody LoginDto loginDto,
                            HttpServletResponse response) {
        Jedis jedis = new Jedis();
        System.out.println(loginDto);
        String MyCode = jedis.get("code");
        if (loginDto.getCheckcode().equals(MyCode)) {
            User user = userService.getOne(new QueryWrapper<User>().eq("u_email", loginDto.getEmail()));
            Assert.notNull(user, "用户不存在");
            String jwt = jwtUtils.generateToken(user.getUId());
            response.setHeader("Authorization", jwt);
            response.setHeader("Access-control-Expose-Headers", "Authorization");
            return Result.success("登录成功", MapUtil.builder()
                    .put("id", user.getUId())
                    .put("u_name", user.getUName())
                    .put("u_sex", user.getUSex())
                    .put("phone", user.getUPhone())
                    .put("u_email", user.getUEmail())
                    .map()
            );
        }
        return Result.error("验证码错误");
    }

    /*@PostMapping("/register")*/
    @RequestMapping("/register")
    public Result register(@RequestBody User user) {
        log.info(String.valueOf(user));
        List<User> users = userService.findUserByName(user.getUName());
        if (users.size() > 0) {
            return Result.error("注册失败，用户名已注册");
        } else if (user.getUPassword() == null || user.getUName() == null) {
            return Result.error("注册失败");

        } else {
            user.setUPassword(DigestUtils.md5DigestAsHex(user.getUPassword().getBytes(StandardCharsets.UTF_8)));
            return userService.save(user) ? Result.success("注册成功", null) : Result.error();
        }
    }

    @PostMapping("/forgetPwd")
    public Result forgetPwd(@RequestBody LoginDto loginDto) {
        List<User> userByEmail = userService.findUserByEmail(loginDto.getEmail());
        if (userByEmail.isEmpty()) {
            return Result.error("该邮箱用户不存在");
        }
        Jedis jedis = new Jedis();
        String code = jedis.get("code");
        if (loginDto.getCheckcode().equals(code)) {
            boolean flag = userService.updatePwdByEmail(loginDto);
            if (flag) {
                return Result.success("重置密码成功");
            }
        }
        return Result.error("重置密码失败");
    }


    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }

}
