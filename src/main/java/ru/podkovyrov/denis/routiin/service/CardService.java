package ru.podkovyrov.denis.routiin.service;

import ru.podkovyrov.denis.routiin.entities.Card;
import ru.podkovyrov.denis.routiin.entities.User;

import java.util.List;

public interface CardService {
    List<Card> findAllUserCards(User user);
    Card findCardById(Long id);
}
