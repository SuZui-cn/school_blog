package com.jueding.schoolblog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 北落燕门
 */


@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
