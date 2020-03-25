package ru.podkovyrov.denis.routiin.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
public class Value {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PARAMETER")
    private int parameter;

    @Column(name = "DATES")
    @Temporal(TemporalType.DATE)
    private Date doneDate;

    @ManyToOne()
    @JoinColumn(name = "CARD_ID")
    @JsonIgnore
    private Card card;

}
