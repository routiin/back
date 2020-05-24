package ru.podkovyrov.denis.routiin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.podkovyrov.denis.routiin.entities.CardTemplate;
import ru.podkovyrov.denis.routiin.payloads.CardResponse;
import ru.podkovyrov.denis.routiin.repository.CardRepository;
import ru.podkovyrov.denis.routiin.repository.CardTemplateRepository;
import ru.podkovyrov.denis.routiin.service.CardTemplateService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardTemplateServiceImpl implements CardTemplateService {
    private final CardTemplateRepository cardTemplateRepository;
    private final CardRepository cardRepository;

    @Autowired
    public CardTemplateServiceImpl(CardTemplateRepository cardRepository, CardRepository cardRepository1) {
        this.cardTemplateRepository = cardRepository;
        this.cardRepository = cardRepository1;
    }
    @Override
    public CardTemplate getById(Long id) {
        return this.cardTemplateRepository.findById(id).get();
    }

    @Override
    public List<CardResponse> findAll() {
        List<CardTemplate> templates = cardTemplateRepository.findAll();
        List<CardResponse> responses = new ArrayList<>();
        for (CardTemplate template : templates) {
            CardResponse card = new CardResponse();
            card.setTitle(template.getTitle());
            card.setDescription(template.getTitle());
            card.setCountOfUsers(cardRepository.countByCardTemplate(template));
            card.setId(template.getId());
            responses.add(card);
        }
        return responses;
    }
}
