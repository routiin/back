package ru.podkovyrov.denis.routiin.service;

import ru.podkovyrov.denis.routiin.entities.CardTemplate;
import ru.podkovyrov.denis.routiin.payloads.CardResponse;

import java.util.List;

public interface CardTemplateService {
    CardTemplate getById(Long id);

    List<CardResponse> findAll();
    void save(CardTemplate newCardTemplate);
}
