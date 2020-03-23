package com.a528854302.mergefiles.controller;

import com.a528854302.mergefiles.dto.FileDto;
import com.a528854302.mergefiles.pojo.ResponseResult;
import com.a528854302.mergefiles.utils.FileToListByDir;
import com.a528854302.mergefiles.utils.MergeFiles;
import com.itextpdf.text.DocumentException;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileContoller {
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Autowired
    private HttpServletRequest request;
    private String userdir;
    @GetMapping("/list")
    public ResponseResult<List<FileDto>> listFiles() throws FileNotFoundException {
        userdir = (String)request.getSession().getAttribute("userdir");
        if (userdir==null || "".equals(userdir)){
            return new ResponseResult(404,"error");
        }
        String path = uploadFolder+"/"+userdir;
        File dir = new File(path);
        if (!dir.exists()){
            return new ResponseResult(404,"error");
        }
        String baseUrl=request.getHeader("Referer")
                +"files/"+userdir+"/";
        return new ResponseResult(FileToListByDir.convert(dir,baseUrl));
    }

    @GetMapping("/remove/{file}")
    public ResponseResult remove(@PathVariable("file") String filename) throws FileNotFoundException {
        userdir = (String)request.getSession().getAttribute("userdir");
        if (userdir==null || "".equals(userdir)){
            return new ResponseResult(404,"error");
        }
        String path = uploadFolder+"/"+userdir;
        File rmFile=new File(path+"/"+filename);
        if (rmFile.exists()){
            if (rmFile.delete()){
                return new ResponseResult();
            }else {
                return new ResponseResult(404,"");
            }
        }else {
            return new ResponseResult(404,"");
        }
    }

    @GetMapping("/merge")
    public ResponseResult merge() throws IOException, DocumentException {
        userdir = (String)request.getSession().getAttribute("userdir");
        if (userdir==null || "".equals(userdir)){
            return new ResponseResult(404,"error");
        }
        String path = uploadFolder+"/"+userdir;
        File dir = new File(path);
        ArrayList<File> srcFiles=new ArrayList();
        if (dir.isDirectory()){
            for (String filename:dir.list()){
                srcFiles.add(new File(path+"/"+filename));
            }
        }
        String mergedFileName=UUID.randomUUID().toString().substring(0,10)+".pdf";
        File mergedFile=new File(path+"/"+ mergedFileName);
        MergeFiles.merge(srcFiles,new File(path+"/"+mergedFileName));
        request.getSession().removeAttribute("userdir");
        return new ResponseResult("/files/"+userdir+"/"+mergedFileName);
    }


}
