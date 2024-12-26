package adam.dev.liveCode.security.jwt.model;

import jakarta.validation.constraints.NotNull;

public class AuthRequest {

    @NotNull(message = "Username cannot be null")
    private String username;

    @NotNull(message = "Password cannot be null")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
