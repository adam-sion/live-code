package adam.dev.liveCode.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoomDTO {

    private String roomName;

    private List<RoomUserDTO> rooms;

}
