package ru.podkovyrov.denis.routiin.service;

import ru.podkovyrov.denis.routiin.entities.Card;
import ru.podkovyrov.denis.routiin.entities.Day;
import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.payloads.CardResponse;

import java.util.List;

public interface DayService {
    Day findByDay(String date);
    List<CardResponse> findAllByUserAndDateBetween(User user, String from, String to);
    void checkDay(User user, Card card, String date);
}
