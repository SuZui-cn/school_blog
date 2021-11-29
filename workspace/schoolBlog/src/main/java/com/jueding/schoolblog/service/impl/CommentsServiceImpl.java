package com.jueding.schoolblog.service.impl;

import com.jueding.schoolblog.entity.Comments;
import com.jueding.schoolblog.mapper.CommentsMapper;
import com.jueding.schoolblog.service.CommentsService;
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
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {

}
