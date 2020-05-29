package ru.podkovyrov.denis.routiin.payloads;

import lombok.Data;

@Data
public class ApiError {
    private String message;

    public ApiError(String message) {
        this.message = message;
    }
}
