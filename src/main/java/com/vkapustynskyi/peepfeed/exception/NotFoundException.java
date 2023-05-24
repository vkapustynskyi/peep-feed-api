package com.vkapustynskyi.peepfeed.exception;

public class NotFoundException extends ApiException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Object... params) {
        super(message, params);
    }
}
