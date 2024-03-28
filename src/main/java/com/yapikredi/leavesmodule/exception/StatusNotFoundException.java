package com.yapikredi.leavesmodule.exception;

public class StatusNotFoundException extends RuntimeException{
    public StatusNotFoundException(String message) {
        super(message);
    }
}
