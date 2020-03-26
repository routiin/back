package ru.podkovyrov.denis.routiin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.podkovyrov.denis.routiin.entities.Card;
import ru.podkovyrov.denis.routiin.entities.Day;

import java.util.Date;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {
    Day findByDate(Date date);
}
