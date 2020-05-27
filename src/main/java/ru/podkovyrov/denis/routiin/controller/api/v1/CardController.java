package ru.podkovyrov.denis.routiin.controller.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.podkovyrov.denis.routiin.entities.Card;
import ru.podkovyrov.denis.routiin.entities.CardTemplate;
import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.exception.ResourceNotFoundException;
import ru.podkovyrov.denis.routiin.payloads.ApiResponse;
import ru.podkovyrov.denis.routiin.payloads.CardRequest;
import ru.podkovyrov.denis.routiin.payloads.CardResponse;
import ru.podkovyrov.denis.routiin.payloads.DayInterval;
import ru.podkovyrov.denis.routiin.repository.UserRepository;
import ru.podkovyrov.denis.routiin.security.CurrentUser;
import ru.podkovyrov.denis.routiin.security.UserPrincipal;
import ru.podkovyrov.denis.routiin.service.CardService;
import ru.podkovyrov.denis.routiin.service.CardTemplateService;
import ru.podkovyrov.denis.routiin.service.DayService;
import ru.podkovyrov.denis.routiin.service.UserService;

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

    public CardController(CardService cardService, UserService userService, CardTemplateService cardTemplateService, DayService dayService, UserRepository userRepository) {
        this.cardService = cardService;
        this.userService = userService;
        this.cardTemplateService = cardTemplateService;
        this.dayService = dayService;
        this.userRepository = userRepository;
    }

    @GetMapping("cards")
    public List<CardResponse> getAllCard(){
        return cardTemplateService.findAll();
    }

    @GetMapping("user/{id}/card")
    public List<Card> getUsersCard(@PathVariable(name = "id") User user){
        // Возвращать CardResponse
        return cardService.findAllUserCards(user);
    }

    @PostMapping("user/me/cards")
    @PreAuthorize("hasRole('USER')")
    public List<CardResponse> getMeCardsFromInterval(@CurrentUser UserPrincipal userPrincipal,
                                                 @RequestBody DayInterval interval){
        User user = userRepository
                .findById(userPrincipal.getId())
                .orElseThrow( () -> {
                    throw new ResourceNotFoundException("User", "id" ,userPrincipal.getId());
                });

        return dayService.findAllByUserAndDateBetween(user, interval.getFrom(), interval.getTo());
    }

    @PostMapping("user/me/cardTemplate/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addMeCardTemplate(@CurrentUser UserPrincipal userPrincipal,
                                               @PathVariable(name = "id") CardTemplate cardTemplate){
        User user = userService.findById(userPrincipal.getId()).get();
        boolean res = cardService.addCardToUser(cardTemplate, user);
        if(res) {
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "You add card"));
        }else {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "You already have this card"));
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

        return ResponseEntity.ok()
                .body(new ApiResponse(true, "You create a card"));
    }
}
