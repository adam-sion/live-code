package adam.dev.liveCode.dto;

import adam.dev.liveCode.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomUserDTO {

    private Role role;

    private boolean isActive;

    private String username;

}
