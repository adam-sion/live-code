package adam.dev.liveCode.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "room_code")
public class RoomCode {

    @EmbeddedId
    private RoomCodeId id;

    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "room_id")
    private Room originalRoom;

    @Column(name = "language", insertable = false, updatable = false)
    private String language;

    @Column(name = "content")
    private String code;

    public RoomCode(Room originalRoom, String language, String code) {
        this.id = new RoomCodeId(originalRoom.getId(), language);
        this.originalRoom = originalRoom;
        this.language = language;
        this.code = code;
    }
}
