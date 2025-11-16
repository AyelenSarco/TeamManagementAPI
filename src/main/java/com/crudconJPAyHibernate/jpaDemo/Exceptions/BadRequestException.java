package com.crudconJPAyHibernate.jpaDemo.Exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
