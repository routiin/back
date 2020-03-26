package ru.podkovyrov.denis.routiin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.podkovyrov.denis.routiin.entities.Card;
import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.service.CardService;

import java.util.List;

@RestController
public class CardController {
    @Autowired
    CardService cardService;

    @GetMapping("user/{id}/card")
    public List<Card> getUsersCard(@PathVariable(name = "id") User user){
        List<Card> cards = cardService.findAllUserCards(user);
        cards.forEach((card)->{
            card.getDays().forEach((day -> {
                System.out.println(day.getDate());
            }));
        });
        return cards;
    }

}
