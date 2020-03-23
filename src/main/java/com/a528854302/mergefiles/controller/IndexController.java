package com.a528854302.mergefiles.controller;

import com.a528854302.mergefiles.dto.FileDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/manage")
    public String manage(){
        return "manage";
    }
    @GetMapping("/test")
    public String test(Model model){
        ArrayList list=new ArrayList();
        for (int i=0;i<30;i++){
            list.add(new FileDto("file"+i,"http://"+i));
        }
        model.addAttribute("list",list);
        return "test";
    }

}
