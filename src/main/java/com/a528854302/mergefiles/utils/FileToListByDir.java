package com.a528854302.mergefiles.utils;

import com.a528854302.mergefiles.dto.FileDto;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;

public class FileToListByDir {
    public static ArrayList<FileDto> convert(File userDir, String baseUrl){
        ArrayList<FileDto> files=new ArrayList<>();
        for (File file:userDir.listFiles()){
            String longFileSize=String.valueOf(file.length()/1048576.0);
            String size=longFileSize.substring(0,longFileSize.lastIndexOf(".")+4);
            files.add(new FileDto(file.getName(),
                    baseUrl+file.getName(),size+" MB"));
        }
        return files;
    }
}
