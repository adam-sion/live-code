package adam.dev.liveCode.controller;

import adam.dev.liveCode.entity.User;
import adam.dev.liveCode.exception.ErrorResponse;
import adam.dev.liveCode.security.jwt.JwtUtil;
import adam.dev.liveCode.service.CustomUserDetailsService;
import adam.dev.liveCode.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private CustomUserDetailsService userDetailsService;

    private UserService userService;

    private JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
         userService.createUser(user);
    }

    @GetMapping("/checkLoggedIn")
    public ResponseEntity<String> checkLoggedIn(@CookieValue String authToken) {

        try {
            String username = jwtUtil.extractUserName(authToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateAuthToken(authToken, userDetails)) {
                return new ResponseEntity<>("User is logged in", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/me")
    public User getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());

        return user;
    }

    @RequestMapping("/**")
    public ResponseEntity<ErrorResponse> handleInvalidEndpoint() {
        return new ResponseEntity<>(new ErrorResponse("Not found", "endpoint not found"), HttpStatus.NOT_FOUND);  // Correct order
    }

}
