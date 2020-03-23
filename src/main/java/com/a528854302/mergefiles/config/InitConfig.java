package com.a528854302.mergefiles.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class InitConfig implements InitializingBean {
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Override
    public void afterPropertiesSet() throws Exception {
        File uploadFoler=new File(uploadFolder);
        if (!uploadFoler.exists()){
            uploadFoler.mkdir();
        }
    }
}
