package ru.podkovyrov.denis.routiin.payloads;

import lombok.Data;

@Data
public class CardResponse {
    private Long id;
    private String title;
    private String description;
    private long countOfUsers;
    private boolean isDone;
}
