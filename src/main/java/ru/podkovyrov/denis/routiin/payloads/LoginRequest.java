package ru.podkovyrov.denis.routiin.payloads;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank
    private String login;

    @NotBlank
    private String password;

}