package com.a528854302.mergefiles.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * InitializingBean接口为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，凡是继承该接口的类，在初始化bean的时候都会执行该方法。
 */
@Component
public class InitConfig implements InitializingBean {
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    /**
     * 判断上传文件的目录是否存在，若不存在则创建目录
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        File uploadFoler=new File(uploadFolder);
        if (!uploadFoler.exists()){
            uploadFoler.mkdir();
        }
    }
}
