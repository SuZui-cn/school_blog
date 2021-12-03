package com.jueding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jueding.entity.Comments;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 北落燕门
 * @since 2021-11-29
 */
public interface CommentsService extends IService<Comments> {

    /**
     * 根据文章ID获取评论
     *
     * @param atId 文章ID
     * @return 评论集合
     */
    List<Comments> getComments(int atId);

    /**
     * 根据文章ID删除评论
     *
     * @param cId 文章ID
     * @return 是否成功
     */
    boolean delComments(int cId);

}
