package com.example.hackforher.Exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class ErrorResponse {

    private int errorCode;
    private Map<String,String> errors;
    private String uri;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    public ErrorResponse() {
        this.timestamp=new Date();
    }

    public ErrorResponse(int errorCode, Map<String,String> errors, String uri) {
        this();
        this.errorCode = errorCode;
        this.errors = errors;
        this.uri = uri;
    }

}
