package ru.podkovyrov.denis.routiin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.podkovyrov.denis.routiin.entities.AuthProvider;
import ru.podkovyrov.denis.routiin.entities.Status;
import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.payloads.SignUpRequest;
import ru.podkovyrov.denis.routiin.repository.UserRepository;
import ru.podkovyrov.denis.routiin.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllByStatus(Status.ACTIVE);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        user.setStatus(Status.DELETED);
        userRepository.save(user);
    }

}
