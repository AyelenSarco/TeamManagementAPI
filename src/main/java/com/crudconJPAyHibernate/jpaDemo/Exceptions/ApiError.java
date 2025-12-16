package com.crudconJPAyHibernate.jpaDemo.Exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


public class ApiError {
    private  String message;
    private  String status;


    public ApiError(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public ApiError() {}

    public String getMessage() {
        return message;
    }
    public String getStatus() {
        return status;
    }


}
