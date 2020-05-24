package ru.podkovyrov.denis.routiin.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "card_template")
public class CardTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @OneToMany(mappedBy = "cardTemplate", cascade = CascadeType.ALL)
    private List<Card> cards;

//    @ElementCollection(targetClass = String.class)
//    private List<String> tasks;
//
//    @ElementCollection(targetClass = String.class)
//    private List<String> startValue;
}

