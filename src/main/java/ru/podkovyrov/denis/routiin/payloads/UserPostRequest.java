package ru.podkovyrov.denis.routiin.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPostRequest {
    private String firstName;
    private String lastName;
    private String login;

}
