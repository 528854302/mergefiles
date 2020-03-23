package com.a528854302.mergefiles.dto;

import java.io.Serializable;

public class UserDirDto implements Serializable {
    private String name;
    private String type;

    public UserDirDto(String name) {
        this.name = name;
        this.type = "用户文件夹";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
