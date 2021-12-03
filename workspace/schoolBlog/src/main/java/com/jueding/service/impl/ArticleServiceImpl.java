package com.jueding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jueding.entity.Article;
import com.jueding.entity.User;
import com.jueding.mapper.ArticleMapper;
import com.jueding.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 北落燕门
 * @since 2021-11-29
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> getAll() {
        return articleMapper.selectList(null);
    }

    @Override
    public Page<Article> getPage(int currentPage, int pageSize, User user) {
        Page<Article> articlePage = new Page<>(currentPage, pageSize);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(user.getUId() != null, "u_id", user.getUId());
        return articleMapper.selectPage(articlePage, queryWrapper);
    }

    @Override
    public boolean delArticleById(int id) {
        return articleMapper.deleteById(id) > 0;
    }
    
}
