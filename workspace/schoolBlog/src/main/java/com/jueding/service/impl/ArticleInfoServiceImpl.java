package com.jueding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jueding.entity.ArticleInfo;
import com.jueding.mapper.ArticleInfoMapper;
import com.jueding.service.ArticleInfoService;
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
public class ArticleInfoServiceImpl extends ServiceImpl<ArticleInfoMapper, ArticleInfo> implements ArticleInfoService {
    @Autowired
    private ArticleInfoMapper articleInfoMapper;
}
