package adam.dev.liveCode.controller;

import adam.dev.liveCode.configuration.jwt.JwtUtil;
import adam.dev.liveCode.configuration.jwt.model.AuthRequest;
import adam.dev.liveCode.configuration.jwt.model.AuthResponse;
import adam.dev.liveCode.entity.User;
import adam.dev.liveCode.service.CustomUserDetailsService;
import adam.dev.liveCode.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    private CustomUserDetailsService userDetailsService;

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    private PasswordEncoder passwordEncoder;


    public AuthController(UserService userService, CustomUserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User userToRegister) {
        userToRegister.setPassword(passwordEncoder.encode(userToRegister.getPassword()));
        userService.createUser(userToRegister);

        return ResponseEntity.ok("User registered successfully!");
    }

}
