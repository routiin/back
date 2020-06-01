package ru.podkovyrov.denis.routiin.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DayInterval {
    String from;
    String to;
}
