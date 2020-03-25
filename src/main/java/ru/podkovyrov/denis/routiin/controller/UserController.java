package ru.podkovyrov.denis.routiin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<User> getAllUsers(){
        return userService.findAll();
    }


    @GetMapping("/user/{id}")
    public User getAllUsers(@PathVariable(name = "id") Long id){
        return userService.findById(id);
    }
}
