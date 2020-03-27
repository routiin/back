package ru.podkovyrov.denis.routiin.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "LOGIN", nullable = true)
    private String login;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    private String imageUrl;

    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @Email
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "CARDS")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Card> cards;
}
