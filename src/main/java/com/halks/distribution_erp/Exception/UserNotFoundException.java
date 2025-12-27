package com.halks.distribution_erp.Exception;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(String message) {
        super(ErrorCode.USER_NOT_FOUND, message);
    }
}
