package adam.dev.liveCode.configuration.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        System.out.println(Arrays.toString(Arrays.stream(authException.getStackTrace()).toArray()));
        // Set the response status code and content type
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");

        // Send a custom error message in the response
        String message = "{ \"error\": \"Unauthorized\", \"message\": \"You must be authenticated to access this resource\" }";
        response.getWriter().write(message);
    }
}

