package adam.dev.liveCode.controller;

import adam.dev.liveCode.dto.UserDTO;
import adam.dev.liveCode.exception.ErrorResponse;
import adam.dev.liveCode.security.jwt.JwtUtil;
import adam.dev.liveCode.security.jwt.model.AuthRequest;
import adam.dev.liveCode.service.CustomUserDetailsService;
import adam.dev.liveCode.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final CustomUserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest authRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateAuthToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(userDetails);


        ResponseCookie authCookie = ResponseCookie.from("authToken", jwt)
                .httpOnly(true)
                .path("/")
                .secure(true)
                .sameSite("Lax")
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .path("/")
                .secure(true)
                .sameSite("Lax")
                .build();

        return ResponseEntity.ok()
                .header("Set-Cookie", authCookie.toString())
                .header("Set-Cookie", refreshCookie.toString())
                .body("login success");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDTO userToRegister) {

        userService.createUser(userToRegister);

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@CookieValue String refreshToken) {
        String username = jwtUtil.extractUserName(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String authToken = jwtUtil.generateAuthToken(userDetails);
        System.out.println("refresh token was asked damnnnnnnnnnnnnnnnnnnnnn");
        ResponseCookie cookie = ResponseCookie.from("authToken", authToken)
                .httpOnly(true)
                .path("/")
                .secure(true)
                .sameSite("Lax")
                .build();

        return ResponseEntity.ok().header("Set-Cookie", cookie.toString())
                .body("Refresh token was successful");
    }

    @RequestMapping("/**")
    public ResponseEntity<ErrorResponse> handleInvalidEndpoint() {
        return new ResponseEntity<>(new ErrorResponse("Not found", "endpoint not found"), HttpStatus.NOT_FOUND);  // Correct order
    }

}
