package com.jueding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jueding.entity.Comments;
import com.jueding.mapper.CommentsMapper;
import com.jueding.service.CommentsService;
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
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;
}
