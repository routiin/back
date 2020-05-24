package ru.podkovyrov.denis.routiin.service.impl;

import org.springframework.stereotype.Service;
import ru.podkovyrov.denis.routiin.entities.Card;
import ru.podkovyrov.denis.routiin.entities.Day;
import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.payloads.CardResponse;
import ru.podkovyrov.denis.routiin.repository.CardRepository;
import ru.podkovyrov.denis.routiin.repository.CardTemplateRepository;
import ru.podkovyrov.denis.routiin.repository.DayRepository;
import ru.podkovyrov.denis.routiin.service.CardService;
import ru.podkovyrov.denis.routiin.service.DayService;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DayServiceImpl implements DayService {
    private final DayRepository dayRepository;
    private final CardRepository cardRepository;
    private final CardTemplateRepository cardTemplateRepository;

    public DayServiceImpl(DayRepository dayRepository, CardRepository cardRepository, CardTemplateRepository cardTemplateRepository) {
        this.dayRepository = dayRepository;
        this.cardRepository = cardRepository;
        this.cardTemplateRepository = cardTemplateRepository;
    }


    @Override
    public Day findByDay(String date) {
       List<Day> test = dayRepository.findAll();
        System.out.println(test.get(0).getDate());
        System.out.println(ZonedDateTime.now(ZoneOffset.UTC));
        return new Day();
    }

    @Override
    public List<CardResponse> findAllByUserAndDateBetween(User user, String from, String to) {
        List<Day> days = dayRepository.findAllByUserAndDateBetween(user, ZonedDateTime.parse(from),
                ZonedDateTime.parse(to));
        List<Card> cards = cardRepository.findAllByUser(user);
        List<CardResponse> responses = new ArrayList<>();
        for (Card card : cards) {
            if(card.getStartDate().isBefore(ZonedDateTime.parse(from).plusDays(1))) {
                CardResponse cardResponse = new CardResponse();
                cardResponse.setId(card.getId());
                cardResponse.setTitle(card.getCardTemplate().getTitle());
                cardResponse.setDescription(card.getCardTemplate().getDescription());
                for (Day day : days) {
                    if (day.getCard().getId().equals(card.getId())) {
                        cardResponse.setDone(true);
                        break;
                    }
                }
                responses.add(cardResponse);
            }
        }
        responses.forEach((cardResponse -> {
            System.out.println(cardResponse.getId());
        }));
        return responses;
    }

    @Override
    public void checkDay(User user, Card card, String date) {
        Day day = new Day();
        day.setCard(card);
        day.setDate(ZonedDateTime.parse(date));
        day.setUser(user);
        dayRepository.save(day);
    }

}
