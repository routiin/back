package ru.podkovyrov.denis.routiin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.podkovyrov.denis.routiin.entities.Card;
import ru.podkovyrov.denis.routiin.entities.CardTemplate;
import ru.podkovyrov.denis.routiin.entities.User;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByUser(User user);
    List<Card> findAllByUserId(Long userId);
    long countByCardTemplate(CardTemplate cardTemplate);
}
