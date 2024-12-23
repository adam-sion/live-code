package adam.dev.liveCode.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "active_room_user", schema = "live_code")
public class ActiveRoomUser {

    @EmbeddedId
    RoomUserId id;

    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private Room room;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "joined_at")
    private LocalDateTime joinedAt;
}
