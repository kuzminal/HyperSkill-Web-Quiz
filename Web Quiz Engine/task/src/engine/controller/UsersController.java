package engine.controller;

import engine.model.entity.User;
import engine.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        if (userService.userExists(user)) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().body(userService.register(user));
        }
    }
}
