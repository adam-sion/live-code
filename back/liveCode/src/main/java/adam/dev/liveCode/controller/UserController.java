package adam.dev.liveCode.controller;

import adam.dev.liveCode.entity.User;
import adam.dev.liveCode.exception.ErrorResponse;
import adam.dev.liveCode.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping("/**")
    public ResponseEntity<ErrorResponse> handleInvalidEndpoint() {
        return new ResponseEntity<>(new ErrorResponse("Not found", "endpoint not found"), HttpStatus.NOT_FOUND);  // Correct order
    }

}
