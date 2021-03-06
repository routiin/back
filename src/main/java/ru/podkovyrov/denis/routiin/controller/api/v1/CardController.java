package ru.podkovyrov.denis.routiin.controller.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.podkovyrov.denis.routiin.entities.Card;
import ru.podkovyrov.denis.routiin.entities.CardTemplate;
import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.exception.ResourceNotFoundException;
import ru.podkovyrov.denis.routiin.payloads.*;
import ru.podkovyrov.denis.routiin.repository.UserRepository;
import ru.podkovyrov.denis.routiin.security.CurrentUser;
import ru.podkovyrov.denis.routiin.security.UserPrincipal;
import ru.podkovyrov.denis.routiin.service.*;

import java.util.List;

import static ru.podkovyrov.denis.routiin.controller.api.v1.ControllerConstants.API_VERSION;

@RestController
@RequestMapping(API_VERSION)
public class CardController {

    private final CardService cardService;
    private final UserService userService;
    private final CardTemplateService cardTemplateService;
    private final DayService dayService;
    private final UserRepository userRepository;
    private final ScoreService scoreService;

    public CardController(CardService cardService, UserService userService, CardTemplateService cardTemplateService, DayService dayService, UserRepository userRepository, ScoreService scoreService) {
        this.cardService = cardService;
        this.userService = userService;
        this.cardTemplateService = cardTemplateService;
        this.dayService = dayService;
        this.userRepository = userRepository;
        this.scoreService = scoreService;
    }

    @PostMapping("user/me/cards/from/interval")
    @PreAuthorize("hasRole('USER')")
    public List<CardResponse> getMeCardsFromInterval(@CurrentUser UserPrincipal userPrincipal,
                                                 @RequestBody DayInterval interval){
        User user = userRepository
                .findById(userPrincipal.getId())
                .orElseThrow( () -> {
                    throw new ResourceNotFoundException("User", "id" ,userPrincipal.getId());
                });

        System.out.println(interval.getFrom() + " " + interval.getTo());
        return dayService.findAllByUserAndDateBetween(user, interval.getFrom(), interval.getTo());
    }

    @PostMapping("user/me/add/cardTemplate/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addCardTemplateToMeUser(@CurrentUser UserPrincipal userPrincipal,
                                               @PathVariable(name = "id") CardTemplate cardTemplate){
        User user = userService.findById(userPrincipal.getId()).get();

        scoreService.addScoreForCreateCard(user.getId());

        boolean res = cardService.addCardToUser(cardTemplate, user);
        if(res) {
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "You add card"));
        }else {
            return ResponseEntity.badRequest()
                    .body(new ApiError("You already have this card"));
        }
    }

    @PostMapping("user/me/create/card")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createNewCard(@CurrentUser UserPrincipal userPrincipal,
                                           @RequestBody CardRequest cardRequest) {
        User user = userRepository.findById(userPrincipal.getId()).get();
        CardTemplate newCardTemplate = new CardTemplate();
        newCardTemplate.setTitle(cardRequest.getTitle());
        newCardTemplate.setDescription(cardRequest.getDescription());

        cardTemplateService.save(newCardTemplate);
        cardService.addCardToUser(newCardTemplate, user);
        scoreService.addScoreForCreateCard(user.getId());

        return ResponseEntity.ok()
                .body(new ApiResponse(true, "You create a card"));
    }

    @PostMapping("user/me/card/{id}/check")
    @PreAuthorize("hasRole('USER')")
    public void setDay(@CurrentUser UserPrincipal userPrincipal,
                       @PathVariable(name = "id") Card card,
                       @RequestBody DayTimeRequest day){
        dayService.checkDay(userRepository.findById(userPrincipal.getId()).get(),card, day.getDate());
    }
}
