package com.dk.flowershop.exception;

public class FlowerShopException extends Exception {

    private ErrorCode errorCode;

    public FlowerShopException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
