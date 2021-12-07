package com.jueding.controller;


import com.jueding.common.lang.Result;
import com.jueding.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 北落燕门
 * @since 2021-11-29
 */
@RestController
@RequestMapping("/article-info")
public class ArticleInfoController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/support/{atId}/{uId}")
    public Result support(@PathVariable("atId") int atId,
                          @PathVariable("uId") int uId) {

        return null;
    }

    @GetMapping("/collection/{atId}/{uId}")
    public Result collection(@PathVariable("atId") int atId,
                             @PathVariable("uId") int uId) {

        return null;
    }
}
