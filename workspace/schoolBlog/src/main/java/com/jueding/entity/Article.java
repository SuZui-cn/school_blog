package com.jueding.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("blog_article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotBlank
    private Integer atId;
    @NotBlank
    private Integer uId;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime atDate;
    @NotBlank
    private String atTitle;

    private String atAbstract;
    @NotBlank
    private String atContent;

    private String atType;


}
