package ru.podkovyrov.denis.routiin.service;

import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.payloads.SignUpRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    User register(SignUpRequest signUpRequest);

    void delete(User user);
}
