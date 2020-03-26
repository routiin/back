package ru.podkovyrov.denis.routiin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.podkovyrov.denis.routiin.entities.Day;
import ru.podkovyrov.denis.routiin.repository.DayRepository;
import ru.podkovyrov.denis.routiin.service.DayService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DayServiceImpl implements DayService {
    @Autowired
    DayRepository dayRepository;

    @Override
    public Day findByDay(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dDate = format.parse(date);
            System.out.println(dDate);
            return dayRepository.findByDate(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
