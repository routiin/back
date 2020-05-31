package ru.podkovyrov.denis.routiin.payloads;

import lombok.Data;

@Data
public class UserPostRequest {
    private String firstName;
    private String lastName;
    private String login;

}
