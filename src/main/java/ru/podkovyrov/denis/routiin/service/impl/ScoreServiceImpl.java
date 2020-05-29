package ru.podkovyrov.denis.routiin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.exception.ResourceNotFoundException;
import ru.podkovyrov.denis.routiin.repository.UserRepository;
import ru.podkovyrov.denis.routiin.service.ScoreService;

public class ScoreServiceImpl implements ScoreService {
    private final UserRepository userRepository;

    @Autowired
    public ScoreServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void addScoreForCreateCard(Long userId) {
        User user = userRepository.findById(userId).orElseThrow( () ->
              new ResourceNotFoundException("User", "id", userId)
        );

        Long score = user.getScore();
        user.setScore(score+5);

        userRepository.save(user);
    }
}
