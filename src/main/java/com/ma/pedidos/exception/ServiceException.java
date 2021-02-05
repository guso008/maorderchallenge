package com.ma.pedidos.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ServiceException extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;

    private final HttpStatus code;

    public ServiceException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }

    public HttpStatus getCode() {
        return code;
    }
}
