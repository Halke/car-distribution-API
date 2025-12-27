package com.halks.distribution_erp.Exception.dto;

import com.halks.distribution_erp.Exception.ErrorCode;
import lombok.Data;

import java.time.Instant;

@Data
public class ErrorResponseDTO {
    private String timestamp;
    private ErrorCode errorCode;
    private String message;

    public ErrorResponseDTO(ErrorCode errorCode, String message) {
        this.timestamp = Instant.now().toString();
        this.errorCode = errorCode;
        this.message = message;
    }
}
