package com.halks.distribution_erp.Exception;

public class ResourceNotFoundException extends CustomException {
    public ResourceNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
