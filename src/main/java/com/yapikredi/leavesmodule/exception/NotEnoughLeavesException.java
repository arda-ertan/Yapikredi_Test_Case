package com.yapikredi.leavesmodule.exception;

public class NotEnoughLeavesException extends RuntimeException{
    public NotEnoughLeavesException(String message) {
        super(message);
    }
}
