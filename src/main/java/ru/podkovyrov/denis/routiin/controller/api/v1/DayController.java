package ru.podkovyrov.denis.routiin.controller.api.v1;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.podkovyrov.denis.routiin.entities.Card;
import ru.podkovyrov.denis.routiin.payloads.DayTimeRequest;
import ru.podkovyrov.denis.routiin.repository.UserRepository;
import ru.podkovyrov.denis.routiin.security.CurrentUser;
import ru.podkovyrov.denis.routiin.security.UserPrincipal;
import ru.podkovyrov.denis.routiin.service.DayService;

import static ru.podkovyrov.denis.routiin.controller.api.v1.ControllerConstants.API_VERSION;

@RestController
@RequestMapping(API_VERSION)
public class DayController {
    private final DayService dayService;
    private final UserRepository userRepository;

    public DayController(DayService dayService, UserRepository userRepository) {
        this.dayService = dayService;
        this.userRepository = userRepository;
    }

    @PostMapping("user/me/card/{id}/check")
    @PreAuthorize("hasRole('USER')")
    public void setDay(@CurrentUser UserPrincipal userPrincipal,
                       @PathVariable(name = "id") Card card,
                       @RequestBody DayTimeRequest day){
        dayService.checkDay(userRepository.findById(userPrincipal.getId()).get(),card, day.getDate());
    }

}
