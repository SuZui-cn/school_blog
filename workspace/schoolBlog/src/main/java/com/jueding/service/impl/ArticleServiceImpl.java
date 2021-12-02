package com.jueding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jueding.entity.Article;
import com.jueding.mapper.ArticleMapper;
import com.jueding.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
