package ru.podkovyrov.denis.routiin.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.podkovyrov.denis.routiin.security.CurrentUser;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Day> days;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "card_template_id")
    private CardTemplate cardTemplate;
}
