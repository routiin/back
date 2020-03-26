package ru.podkovyrov.denis.routiin.service;

import ru.podkovyrov.denis.routiin.entities.Day;

public interface DayService {
    Day findByDay(String date);
}
