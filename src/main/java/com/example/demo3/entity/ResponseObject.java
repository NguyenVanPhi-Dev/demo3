package com.example.demo3.entity;

import javax.persistence.Entity;
import java.util.Optional;

public class ResponseObject {
    private int statusCode;
    private String contentType;
    private Object data;

    public ResponseObject(int statusCode, String contentType, Object data) {
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.data = data;
    }

    public ResponseObject() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
