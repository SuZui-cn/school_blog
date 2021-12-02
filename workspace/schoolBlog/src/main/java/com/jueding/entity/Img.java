package com.jueding.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

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
    @NotBlank
    private Integer iId;

    private String iPath;
    @NotBlank
    private Integer atId;


}
