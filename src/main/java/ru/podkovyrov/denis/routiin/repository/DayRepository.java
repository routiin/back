package ru.podkovyrov.denis.routiin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.podkovyrov.denis.routiin.entities.Day;
import ru.podkovyrov.denis.routiin.entities.User;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {
    Day findByDate(ZonedDateTime date);
    List<Day> findAllByUserAndDateBetween(User user, ZonedDateTime from, ZonedDateTime to);
}
