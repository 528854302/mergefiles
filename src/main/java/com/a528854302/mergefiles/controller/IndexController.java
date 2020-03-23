package com.a528854302.mergefiles.controller;

import com.a528854302.mergefiles.dto.FileDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

/**
 * 根据url返回html页面
 */
@Controller
public class IndexController {
    /**
     * 跳转首页
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 跳转到后台页面
     * @return
     */
    @GetMapping("/manage")
    public String manage(){
        return "manage";
    }

}
