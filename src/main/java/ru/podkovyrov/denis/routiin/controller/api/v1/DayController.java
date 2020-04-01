package ru.podkovyrov.denis.routiin.controller.api.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.podkovyrov.denis.routiin.entities.Card;
import ru.podkovyrov.denis.routiin.entities.Day;
import ru.podkovyrov.denis.routiin.service.DayService;

import static ru.podkovyrov.denis.routiin.controller.api.v1.ControllerConstants.API_VERSION;

@RestController
@RequestMapping(API_VERSION)
public class DayController {
    private final DayService dayService;

    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @GetMapping("card/{id}/day/{date}")
    public Day getDay(@PathVariable(name = "id") Card card,
                      @PathVariable(name = "date")String date){
        return dayService.findByDay(date);
    }
}
