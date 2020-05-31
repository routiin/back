package ru.podkovyrov.denis.routiin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.podkovyrov.denis.routiin.entities.Status;
import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.exception.BadRequestException;
import ru.podkovyrov.denis.routiin.payloads.UserMeResponse;
import ru.podkovyrov.denis.routiin.payloads.UserPostRequest;
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

    @Override
    public UserMeResponse updateUserInfo(User user, UserPostRequest newInfo) {
        String newLogin = newInfo.getLogin();
        String newFirstName = newInfo.getFirstName();
        String newLastName = newInfo.getLastName();

        if (newLogin != null && !userRepository.existsByLogin(newLogin)) {
            user.setLogin(newInfo.getLogin());
        } else  {
            throw new BadRequestException("login " + newLogin + " already used");
        }

        if (newFirstName != null) {
            user.setFirstName(newFirstName);
        }

        if (newLastName != null) {
            user.setLastName(newLastName);
        }

        userRepository.save(user);
        return new UserMeResponse(userRepository.findById(user.getId()).get());
    }

}
