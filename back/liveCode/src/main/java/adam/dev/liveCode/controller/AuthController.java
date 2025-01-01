package adam.dev.liveCode.controller;

import adam.dev.liveCode.entity.User;
import adam.dev.liveCode.exception.ErrorResponse;
import adam.dev.liveCode.security.jwt.JwtUtil;
import adam.dev.liveCode.security.jwt.model.AuthRequest;
import adam.dev.liveCode.service.CustomUserDetailsService;
import adam.dev.liveCode.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest authRequest) {
        if (authRequest == null) {
            throw new HttpMessageNotReadableException("Request body is missing or malformed");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        ResponseCookie cookie = ResponseCookie.from("authToken", jwt)
                .httpOnly(true)
                .path("/")
                .sameSite("Lax")
                .secure(true)
                .build();

        return ResponseEntity.ok()
                .header("Set-Cookie", cookie.toString())
                .body("Login was successful");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody @Valid User userToRegister) {
        if (userToRegister == null) {
            throw new HttpMessageNotReadableException("Request body is missing or malformed");
        }

        userToRegister.setPassword(passwordEncoder.encode(userToRegister.getPassword()));
        userService.createUser(userToRegister);

        return ResponseEntity.ok("User registered successfully!");
    }

    @RequestMapping("/**")
    public ResponseEntity<ErrorResponse> handleInvalidEndpoint() {
        return new ResponseEntity<>(new ErrorResponse("Not found", "endpoint not found"), HttpStatus.NOT_FOUND);  // Correct order
    }

}
