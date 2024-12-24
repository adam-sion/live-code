package adam.dev.liveCode.configuration.jwt.model;

public class AuthResponse {

    private String jwtToken;

    public AuthResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

}
