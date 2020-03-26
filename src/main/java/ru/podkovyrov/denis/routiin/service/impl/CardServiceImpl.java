package ru.podkovyrov.denis.routiin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.podkovyrov.denis.routiin.entities.Card;
import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.repository.CardRepository;
import ru.podkovyrov.denis.routiin.service.CardService;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> findAllUserCards(User user) {
        return cardRepository.findAllByUser(user);
    }

    @Override
    public Card findCardById(Long id) {
        return cardRepository.findById(id).get();
    }
}
