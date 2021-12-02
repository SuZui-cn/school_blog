package com.jueding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jueding.entity.Img;
import com.jueding.mapper.ImgMapper;
import com.jueding.service.ImgService;
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
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img> implements ImgService {
    @Autowired
    private ImgMapper imgMapper;
}
