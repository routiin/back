package ru.podkovyrov.denis.routiin.payloads;

import lombok.Data;

import javax.persistence.Column;

@Data
public class CardRequest {
    private String title;
    private String description;
}
