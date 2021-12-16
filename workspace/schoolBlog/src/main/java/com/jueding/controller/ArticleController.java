package com.jueding.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jueding.common.lang.Result;
import com.jueding.entity.Article;
import com.jueding.entity.User;
import com.jueding.service.ArticleService;
import com.jueding.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
@Slf4j
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    /**
     * 查询所有文章的接口
     *
     * @return 通用返回集
     */
//    @RequiresAuthentication
    @GetMapping("/getAll")
    public Result getAll() {
        ArrayList<Article> articleList = (ArrayList<Article>) articleService.getAll();
        for (Article article : articleList) {
            List<User> userById = userService.findUserById(article.getUId());
            User user = userById.get(0);
            article.setUName(user.getUName());
        }
        return articleList.size() != 0 ? Result.success(articleList) : Result.error("查询为空");
    }

    /**
     * 根据用户ID查询文章的接口
     *
     * @param uId 用户id
     * @return 通用返回集
     */
    @RequiresAuthentication
    @GetMapping("/getOne/{uId}")
    public Result getOne(@PathVariable("uId") int uId) {
        List<Article> articles = articleService.findByUserId(uId);
        List<User> userById = userService.findUserById(uId);
        User user = userById.get(0);
        for (Article article : articles) {
            article.setUName(user.getUName());
        }
        return articles.size() > 0 ? Result.success(articles) : Result.error();
    }


    /**
     * 文章分页查询
     *
     * @param currentPage 当前页数
     * @param pageSize    每页大小
     * @param article     文章信息（全部查询可为空）
     * @return 通用返回集
     */
//    @RequiresAuthentication
    @GetMapping("/getPage/{currentPage}/{pageSize}")
    public Result getPage(@PathVariable("currentPage") int currentPage,
                          @PathVariable("pageSize") int pageSize,
                          Article article) {
        Page<Article> page = articleService.getPage(currentPage, pageSize, article);
        //查询超出最大页数时默认查询最后一页
        if (currentPage > page.getPages()) {
            page = articleService.getPage((int) page.getPages(), pageSize, article);
        }
        return page.getTotal() != 0 ? Result.success(page) : Result.error("查询失败");
    }

    /**
     * 保存文章接口,如果文章ID存在则进行修改
     *
     * @param article 文章实体
     * @return 通用返回集
     */
    @RequiresAuthentication
    @PostMapping("/save/{id}")
    public Result save(@Validated @RequestBody Article article, @PathVariable("id") int id) {
        List<User> userById = userService.findUserById(id);
        article.setUId(id);
        if (userById.size() == 0) {
            return Result.error();
        }
        article.setAtDate(LocalDateTime.now());
        boolean flag = articleService.updateById(article);
        log.info(String.valueOf(flag));
        if (flag) {
            return Result.success();
        }
        return articleService.save(article) ? Result.success() : Result.error();
    }

    /**
     * 更新文章接口
     *
     * @param article 文章实体
     * @return 通用返回集
     */
//    @PutMapping
//    @RequiresAuthentication
//    public Result updataArticle(@Validated @RequestBody Article article) {
//        boolean flag = articleService.updateById(article);
//        return flag ? Result.success() : Result.error("操作失败");
//    }

    /**
     * 删除文章接口
     *
     * @param atId 文章Id
     * @return 通用返回集
     */
    @RequiresAuthentication
    @DeleteMapping("{atId}")
    public Result delArticle(@PathVariable("atId") int atId) {
        boolean flag = articleService.delArticleById(atId);
        return flag ? Result.success() : Result.error("更新失败");
    }
}
