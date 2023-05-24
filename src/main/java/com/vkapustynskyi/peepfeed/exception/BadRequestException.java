package com.vkapustynskyi.peepfeed.exception;

public class BadRequestException extends ApiException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Object... params) {
        super(message, params);
    }
}
