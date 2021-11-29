package com.jueding.schoolblog.controller;


import com.jueding.schoolblog.common.Result;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 北落燕门
 * @since 2021-11-29
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @PostMapping("{id}")
    public Result login(@PathVariable String id){
        return new Result();
    }
}
