package com.example.hackforher.Exception;

import org.springframework.http.HttpStatus;

public class ResourceExistException extends ApiBaseException{

    public ResourceExistException(int errorCode,String message) {
        super(message,errorCode);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }
}
