package ru.podkovyrov.denis.routiin.entities;

import lombok.Cleanup;
import lombok.Data;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.sql.Timestamp;
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
    private Timestamp createdDate;

    @ElementCollection(targetClass = String.class)
    private List<String> tasks;

    @ElementCollection(targetClass = String.class)
    private List<String> startValue;

    @OneToMany(mappedBy = "cardTemplate", cascade = CascadeType.ALL)
    private List<Card> cards;
}

