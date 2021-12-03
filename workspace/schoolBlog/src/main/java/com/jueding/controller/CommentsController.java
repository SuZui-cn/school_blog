package com.jueding.controller;


import com.jueding.common.Result;
import com.jueding.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 北落燕门
 * @since 2021-11-29
 */
@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;


    /**
     * 查看文章评论的接口
     *
     * @param atId 文章ID
     * @return 通用返回集
     */
    @GetMapping("getOne/{atId}")
    public Result getOne(@PathVariable("atId") int atId) {
        return null;
    }

    /**
     * 删除评论的接口
     *
     * @param cId 评论ID
     * @return 通用返回集
     */
    @DeleteMapping("{cId}")
    public Result delComments(@PathVariable("cId") int cId) {
        return null;
    }


}
