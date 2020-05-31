package ru.podkovyrov.denis.routiin.service;

import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.payloads.SignUpRequest;
import ru.podkovyrov.denis.routiin.payloads.UserMeResponse;
import ru.podkovyrov.denis.routiin.payloads.UserPostRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    User save(User user);

    void delete(User user);

    UserMeResponse updateUserInfo(User user, UserPostRequest newInfo);
}
