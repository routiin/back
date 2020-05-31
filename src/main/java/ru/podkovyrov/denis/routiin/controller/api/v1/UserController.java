package ru.podkovyrov.denis.routiin.controller.api.v1;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.podkovyrov.denis.routiin.entities.Status;
import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.exception.ResourceNotFoundException;
import ru.podkovyrov.denis.routiin.payloads.UserMeResponse;
import ru.podkovyrov.denis.routiin.payloads.UserPostRequest;
import ru.podkovyrov.denis.routiin.security.CurrentUser;
import ru.podkovyrov.denis.routiin.security.UserPrincipal;
import ru.podkovyrov.denis.routiin.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static ru.podkovyrov.denis.routiin.controller.api.v1.ControllerConstants.API_VERSION;

@RestController
@RequestMapping(API_VERSION)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserMeResponse getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        User user =  userService.findById(userPrincipal.getId())
                .orElseThrow(()
                        -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
        return new UserMeResponse(user);
    }

    @GetMapping("/users")
    public List<UserMeResponse> getAllUsers(){
        List<UserMeResponse> users = new ArrayList<>();
        for (User user : userService.findAll()) {
            users.add(new UserMeResponse(user));
        }
        return users;
    }

    @GetMapping("/user/{id}")
    public UserMeResponse getUser(@PathVariable(name = "id") Long id){
        User user = userService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));
        if (user.getStatus() == Status.DELETED) {
            throw new ResourceNotFoundException("User", "id", user.getId());
        }
        return new UserMeResponse(user);
    }

    @PostMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserMeResponse updateUserInfo(@CurrentUser UserPrincipal userPrincipal,
                                         @RequestBody UserPostRequest newInfo){
        User user = userService.findById(userPrincipal.getId()).get();
        System.out.println(user.getFirstName());
        return userService.updateUserInfo(user, newInfo);
    }

}
