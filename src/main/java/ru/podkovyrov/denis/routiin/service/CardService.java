package ru.podkovyrov.denis.routiin.service;

import org.apache.tomcat.jni.Local;
import ru.podkovyrov.denis.routiin.entities.Card;
import ru.podkovyrov.denis.routiin.entities.CardTemplate;
import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.payloads.CardResponse;

import java.time.LocalDate;
import java.util.List;

public interface CardService {
    List<Card> findAll();
    List<Card> findAllUserCards(User user);
    List<CardResponse> findAllByUserId(Long userId);
    Card findCardById(Long id);
    long countOfUsers(CardTemplate cardTemplate);
    boolean addCardToUser(CardTemplate cardTemplate, User user);
}
