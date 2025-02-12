package adam.dev.liveCode.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class JoinRoomDTO {

    private String roomName;

    private Long userId;

}
