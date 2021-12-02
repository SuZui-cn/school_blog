package com.jueding.mapper;

import com.jueding.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 北落燕门
 * @since 2021-11-29
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 查询所有
     * @return 返回管理员集合
     */
    List<Admin> getAll();

    /**
     * 插入测试用例
     * @param admin
     * @return
     */
    int insertTest(Admin admin);


}
