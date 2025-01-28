package adam.dev.liveCode.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {

    @NotNull(message = "can't be null")
    private String username;

    @NotNull(message = "can't be null")
    private String password;

    @NotNull(message = "can't be null")
    private String email;

}