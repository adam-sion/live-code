package adam.dev.liveCode.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Embeddable
@Data
public class RoomCodeId implements Serializable {

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "language")
    private String language;

    public RoomCodeId() {}

    public RoomCodeId(Long roomId, String language) {
        this.roomId = roomId;
        this.language = language;
    }
}
