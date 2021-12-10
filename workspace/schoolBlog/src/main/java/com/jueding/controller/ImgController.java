package com.jueding.controller;


import com.jueding.service.ImgService;
import com.jueding.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 北落燕门
 * @since 2021-11-29
 */
@RestController
@RequestMapping("/api")
public class ImgController {

    @Autowired
    private ImgService imgService;

    @PostMapping("/imgUpload")
    public String coversUpload(MultipartFile file) throws Exception {
        String folder = "F:\\code\\my_repositories\\school_blog\\school_blog\\workspace";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils
                .getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:80/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


}
