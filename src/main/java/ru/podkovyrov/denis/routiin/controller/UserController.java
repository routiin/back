package ru.podkovyrov.denis.routiin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.exception.ResourceNotFoundException;
import ru.podkovyrov.denis.routiin.repository.UserRepository;
import ru.podkovyrov.denis.routiin.security.CurrentUser;
import ru.podkovyrov.denis.routiin.security.UserPrincipal;
import ru.podkovyrov.denis.routiin.service.UserService;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.findAll();
    }


    @GetMapping("/user/{id}")
    public User getAllUsers(@PathVariable(name = "id") Long id){
        return userService.findById(id);
    }
}
