package com.crudconJPAyHibernate.jpaDemo.Exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class CustomizedException {
    private final String message;
    private final HttpStatus status;
    private final ZonedDateTime dateTime;

    public CustomizedException(String message, HttpStatus status, ZonedDateTime dateTime) {
        this.message = message;
        this.status = status;
        this.dateTime = dateTime;
    }
    public String getMessage() {
        return message;
    }
    public HttpStatus getStatus() {
        return status;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }
}
