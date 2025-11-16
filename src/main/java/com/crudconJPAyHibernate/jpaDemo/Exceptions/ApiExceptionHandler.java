package com.crudconJPAyHibernate.jpaDemo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {

        CustomizedException customException = new CustomizedException(ex.getMessage(),
                                                                        HttpStatus.BAD_REQUEST,
                                                                        ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
    }

    // NO SERIA NECESARIO... LO PODEMOS HACER CON EL ANTERIOR
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflictException(ConflictException ex) {

        CustomizedException customException = new CustomizedException(ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        CustomizedException customException = new CustomizedException( ex.getMessage(),
                                                                        HttpStatus.NOT_FOUND,
                                                                        ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(customException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneral(Exception ex) {
        CustomizedException customException = new CustomizedException( "Internal Server Error",
                                                                        HttpStatus.INTERNAL_SERVER_ERROR,
                                                                            ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(customException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }



}
