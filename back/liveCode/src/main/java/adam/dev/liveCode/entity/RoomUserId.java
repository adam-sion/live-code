package adam.dev.liveCode.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class RoomUserId implements Serializable {

    private Long roomId;

    private Long userId;

    public RoomUserId() {}

}
