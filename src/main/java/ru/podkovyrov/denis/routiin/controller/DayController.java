package ru.podkovyrov.denis.routiin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.podkovyrov.denis.routiin.entities.Card;
import ru.podkovyrov.denis.routiin.entities.Day;
import ru.podkovyrov.denis.routiin.service.DayService;

@RestController
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
