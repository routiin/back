package ru.podkovyrov.denis.routiin.controller.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.exception.ResourceNotFoundException;
import ru.podkovyrov.denis.routiin.payloads.ApiResponse;
import ru.podkovyrov.denis.routiin.payloads.ChangePasswordRequest;
import ru.podkovyrov.denis.routiin.payloads.UserMeResponse;
import ru.podkovyrov.denis.routiin.security.CurrentUser;
import ru.podkovyrov.denis.routiin.security.UserPrincipal;
import ru.podkovyrov.denis.routiin.service.UserService;

import javax.validation.Valid;
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
    public List<User> getAllUsers(){
        return userService.findAll();
    }


    @GetMapping("/user/{id}")
    public User getUser(@PathVariable(name = "id") Long id){
        return userService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));
    }


    @PostMapping("/user/me/password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> setPassword(@CurrentUser UserPrincipal userPrincipal,
                                         @Valid @RequestBody ChangePasswordRequest ChangePasswordRequest){
        User user = userService.findById(userPrincipal.getId()).orElse(null);
        if(user==null) {
            return ResponseEntity.ok(new ApiResponse(false, "user not found"));
        }
        userService.changePassword(user, ChangePasswordRequest.getPassword());
        return ResponseEntity.ok(new ApiResponse(true, "password was changed"));
    }
}