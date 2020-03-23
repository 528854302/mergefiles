package com.a528854302.mergefiles.pojo;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable {
    private int code;
    private String message;
    private T data;

    public ResponseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult() {
        this.code=200;
    }

    public ResponseResult(T data) {
        this.data = data;
        this.code=200;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
