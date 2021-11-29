package com.jueding.schoolblog.service.impl;

import com.jueding.schoolblog.entity.Article;
import com.jueding.schoolblog.mapper.ArticleMapper;
import com.jueding.schoolblog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 北落燕门
 * @since 2021-11-29
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
