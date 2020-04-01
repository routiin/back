package ru.podkovyrov.denis.routiin.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
public class Day {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "value")
    @ElementCollection(targetClass = Integer.class)
    private List<Integer> values;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "card_id")
    private Card card;
}
