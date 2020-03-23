package com.a528854302.mergefiles.service;

import com.a528854302.mergefiles.pojo.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UploadService {
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    static Map<String,String>map = new HashMap<>();
    public ResponseResult upload(MultipartFile file, HttpServletRequest request) {
        String originalFileName = file.getOriginalFilename();
        String type = originalFileName.substring(originalFileName.length() - 4, originalFileName.length()).toLowerCase();
        if (!type.equals(".pdf")){
            return new ResponseResult(404,"文件类型不合法");
        }

        HttpSession session = request.getSession();
        String userdir = (String)session.getAttribute("userdir");
        if (userdir==null || "".equals(userdir)){
            userdir=String.valueOf(System.currentTimeMillis()).substring(0,10);
            session.setAttribute("userdir",userdir);
        }
        String path = null;
//        path = Thread.currentThread().getContextClassLoader().getResource("").getPath()
//                +"static/files/"+userdir;
        path=uploadFolder+"/"+userdir;
        File desFile = new File(path,originalFileName);
        if (!desFile.getParentFile().exists()){
            desFile.getParentFile().mkdir();
        }
        try {
            file.transferTo(desFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseResult("http://localhost/files/"+desFile.getName());
    }
}
