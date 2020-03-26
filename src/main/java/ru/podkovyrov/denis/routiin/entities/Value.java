package ru.podkovyrov.denis.routiin.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Value {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PARAMETER")
    private int parameter;

    @ManyToOne()
    @JoinColumn(name = "DAY_ID")
    @JsonIgnore
    private Day day;

}
