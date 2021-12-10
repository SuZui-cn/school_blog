package com.jueding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jueding.entity.Article;
import com.jueding.mapper.ArticleMapper;
import com.jueding.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
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
@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> getAll() {
        return articleMapper.selectList(null);
    }

    @Override
    public Page<Article> getPage(int currentPage, int pageSize, Article article) {
        Page<Article> articlePage = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(article.getAtTitle()), Article::getAtTitle, article.getAtTitle())
                .or().like(Strings.isNotEmpty(article.getAtAbstract()), Article::getAtAbstract, article.getAtAbstract())
                .or().like(Strings.isNotEmpty(article.getAtContent()), Article::getAtContent, article.getAtContent())
                .or().like(Strings.isNotEmpty(article.getAtType()), Article::getAtType, article.getAtType());
        return articleMapper.selectPage(articlePage, lqw);
    }

    @Override
    public List<Article> findByUserId(int uid) {
        QueryWrapper<Article> qw = new QueryWrapper<>();
        qw.eq("u_id", uid);
        List<Article> articles = articleMapper.selectList(qw);
        return articles;
    }

    @Override
    public boolean delArticleById(int id) {
        return articleMapper.deleteById(id) > 0;
    }

}
