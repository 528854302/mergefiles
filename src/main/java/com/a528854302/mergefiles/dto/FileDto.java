package com.a528854302.mergefiles.dto;

import java.io.Serializable;

public class FileDto implements Serializable {
    private String name;
    private String path;
    private String size;
    private String type;

    public FileDto() {
    }

    public FileDto(String name,String path) {
        this.name = name;
        this.path=path;
        this.type ="pdf文件";
    }

    public FileDto(String name, String path, String size) {
        this.name = name;
        this.path = path;
        this.size = size;
        this.type ="pdf文件";
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
