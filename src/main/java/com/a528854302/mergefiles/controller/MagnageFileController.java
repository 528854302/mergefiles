package com.a528854302.mergefiles.controller;

import com.a528854302.mergefiles.dto.FileDto;
import com.a528854302.mergefiles.dto.UserDirDto;
import com.a528854302.mergefiles.pojo.ResponseResult;
import com.a528854302.mergefiles.utils.FileToListByDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * 管理后台api
 */
@RestController
@RequestMapping("/manage")
public class MagnageFileController {
    @Autowired
    HttpServletRequest request;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    /**
     * 查询用户目录
     * @return
     * @throws FileNotFoundException
     */
    @GetMapping("/listDir")
    public ResponseResult<ArrayList<UserDirDto>> listDir() throws FileNotFoundException {
        String path = uploadFolder;
        File filesDir=new File(path);
        ArrayList<UserDirDto> dirs=new ArrayList<>();
        for (String name:filesDir.list()){
            dirs.add(new UserDirDto(name));
        }
        return new ResponseResult<>(dirs);
    }

    /**
     * 根据用户文件夹名查询该文件夹下的文件
     * @param dir 用户文件夹名
     * @return
     * @throws FileNotFoundException
     */
    @GetMapping("/getFilesByUserDir/{dir}")
    public ResponseResult<ArrayList<FileDto>> getFilesByUserDir(@PathVariable("dir")
                                                                String dir) throws FileNotFoundException {
        String path = uploadFolder+"/"+dir;
        File userDir=new File(path);
        if (!userDir.exists()){
            return new ResponseResult<>(404,"");
        }
        String baseUrl= request.getHeader("Referer").substring(0,request.getHeader("Referer").length()-"manage".length())
                +"files/"+dir+"/";

        return new ResponseResult<>(FileToListByDir.convert(userDir,baseUrl));
    }

    /**
     * 根据文件名或文件夹名删除文件或文件夹
     * @param name 文件名或文件夹名
     * @param path  如果是文件夹，值为dir，如果是pdf文件，值为用户文件夹名称
     * @return
     * @throws FileNotFoundException
     */
    @GetMapping("/delete/{name}/{path}")
    public ResponseResult<String> delete(@PathVariable("name") String name
                                        ,@PathVariable("path") String path) throws FileNotFoundException {
        String path1 = uploadFolder;
        File dir=new File(path1+"/"+name);
        if ("dir".equals(path)){
            for (File f:dir.listFiles()){
                f.delete();
            }
            dir.delete();
            return new ResponseResult<>("dir");
        }else {
            new File(path1+"/"+path+"/"+name).delete();
            return new ResponseResult<>("file");
        }
    }

}
