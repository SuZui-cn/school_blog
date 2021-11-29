package com.jueding.schoolblog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 北落燕门
 * @since 2021-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("blog_img")
public class Img implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer iId;

    private String iPath;

    private Integer atId;


}
