package ru.podkovyrov.denis.routiin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.podkovyrov.denis.routiin.entities.Card;
import ru.podkovyrov.denis.routiin.entities.CardTemplate;

import java.util.List;

public interface CardTemplateRepository extends JpaRepository<CardTemplate, Long> {
}
