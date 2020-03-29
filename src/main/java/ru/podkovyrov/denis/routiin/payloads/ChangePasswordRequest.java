package ru.podkovyrov.denis.routiin.payloads;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordRequest {
    @NotBlank
    private String password;
}
