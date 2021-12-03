package com.jueding.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jueding.entity.Article;
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
public interface ArticleService extends IService<Article> {

    /**
     * 查询所有文章
     *
     * @return 文章集合
     */
    List<Article> getAll();


    /**
     * 分页查询
     *
     * @param currentPage 当前页
     * @param pageSize    每页大小
     * @param user        文章作者
     * @return 文章页对象
     */
    Page<Article> getPage(int currentPage, int pageSize, User user);

    /**
     * 根据文章id删除文章
     *
     * @param id 文章id
     * @return 是否成功
     */
    boolean delArticleById(int id);


}
