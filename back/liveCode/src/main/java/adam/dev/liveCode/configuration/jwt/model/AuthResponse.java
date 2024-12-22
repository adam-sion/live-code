package adam.dev.liveCode.configuration.jwt.model;

public class AuthResponse {

     public AuthResponse(String jwtToken) {
         this.jwtToken = jwtToken;
     }

    private String jwtToken;

    public String getJwtToken() {
        return jwtToken;
    }

}
