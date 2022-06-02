package com.stevenson.parcel.api.controller.exception;

public class ApiRejectException extends RuntimeException{

    public ApiRejectException(String message){
        super(message);
    }

    public ApiRejectException(String message, Throwable cause){
        super(message, cause);
    }

}
