package ru.podkovyrov.denis.routiin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.podkovyrov.denis.routiin.entities.Card;
import ru.podkovyrov.denis.routiin.entities.CardTemplate;
import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.payloads.CardResponse;
import ru.podkovyrov.denis.routiin.repository.CardRepository;
import ru.podkovyrov.denis.routiin.repository.CardTemplateRepository;
import ru.podkovyrov.denis.routiin.service.CardService;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public List<Card> findAllUserCards(User user) {
        return cardRepository.findAllByUser(user);
    }

    @Override
    public List<CardResponse> findAllByUserId(Long userId) {
        List<Card> cards = cardRepository.findAllByUserId(userId);

        List<CardResponse> responses = new ArrayList<>();
        for(Card card : cards) {
            CardResponse cardResponse = new CardResponse();
            cardResponse.setCountOfUsers(cardRepository
                    .countByCardTemplate(card.getCardTemplate()));
            cardResponse.setTitle(card.getCardTemplate().getTitle());
            cardResponse.setDescription(card.getCardTemplate().getDescription());
            cardResponse.setId(card.getId());

            responses.add(cardResponse);
        }
        return responses;
    }

    @Override
    public Card findCardById(Long id) {
        return cardRepository.findById(id).get();
    }

    @Override
    public long countOfUsers(CardTemplate cardTemplate) {
        return cardRepository.countByCardTemplate(cardTemplate);
    }

    @Override
    public boolean addCardToUser(CardTemplate cardTemplate, User user) {
        List<Card> cards = user.getCards();
        for (Card c : cards) {
            if (c.getCardTemplate() == cardTemplate) {
                return false;
            }
        }
        Card card = new Card();
        card.setUser(user);
        card.setStartDate(ZonedDateTime.now());
        card.setCardTemplate(cardTemplate);

        cardRepository.save(card);
        return true;
    }

}
