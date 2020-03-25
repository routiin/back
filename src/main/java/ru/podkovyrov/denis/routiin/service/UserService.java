package ru.podkovyrov.denis.routiin.service;

import ru.podkovyrov.denis.routiin.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);

}
