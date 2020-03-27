package ru.podkovyrov.denis.routiin.service;

import ru.podkovyrov.denis.routiin.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    Optional<User> findByEmail(String email);
    void register(User user);

}
