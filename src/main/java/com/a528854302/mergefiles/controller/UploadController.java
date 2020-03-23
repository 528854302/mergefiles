package com.a528854302.mergefiles.controller;

import com.a528854302.mergefiles.pojo.ResponseResult;
import com.a528854302.mergefiles.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UploadController {
    @Autowired
    UploadService service;

    @PostMapping("/upload")
    public ResponseResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        return service.upload(file,request);
    }
}
