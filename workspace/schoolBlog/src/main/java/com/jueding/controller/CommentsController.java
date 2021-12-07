package com.jueding.controller;


import com.jueding.common.lang.Result;
import com.jueding.entity.Comments;
import com.jueding.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result getComment(@PathVariable("atId") int atId) {
        List<Comments> comments = commentsService.getComments(atId);
        return comments.size() != 0 ? Result.success(comments) : Result.error();
    }

    /**
     * 添加评论的接口
     *
     * @param comments 评论实体
     * @return 通用返回集
     */
    @PostMapping("save")
    public Result save(@RequestBody Comments comments) {
        return commentsService.save(comments) ? Result.success() : Result.error("评论失败");
    }


    /**
     * 删除评论的接口
     *
     * @param cId 评论ID
     * @return 通用返回集
     */
    @DeleteMapping("{cId}")
    public Result delComments(@PathVariable("cId") int cId) {
        return commentsService.delComments(cId) ? Result.success() : Result.error();
    }


}
