package ru.podkovyrov.denis.routiin.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
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

    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "VALUE")
    @ElementCollection(targetClass = Integer.class)
    private List<Integer> values;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "CADR_ID")
    private Card card;
}
