package adam.dev.liveCode.dto;

import adam.dev.liveCode.entity.Status;
import lombok.Data;

@Data
public class RoomUserRequestDTO {

    private Long roomUserRequestId;

    private Status status;

}
