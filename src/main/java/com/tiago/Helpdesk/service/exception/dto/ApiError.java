package com.tiago.Helpdesk.service.exception.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiError(String message, String error, int status, String timestamp) {
    public ApiError(String message, HttpStatus status) {
        this(
                message, status.name(), status.value(), LocalDateTime.now().toString()
        );
    }
}
