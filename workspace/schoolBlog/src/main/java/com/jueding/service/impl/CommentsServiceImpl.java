package com.jueding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jueding.entity.Comments;
import com.jueding.mapper.CommentsMapper;
import com.jueding.service.CommentsService;
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
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;


    @Override
    public List<Comments> getComments(int atId) {
        QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("at_id", atId);
        return commentsMapper.selectList(queryWrapper);
    }

    @Override
    public boolean delComments(int cid) {
        int i = commentsMapper.deleteById(cid);
        return i > 0;
    }
}
