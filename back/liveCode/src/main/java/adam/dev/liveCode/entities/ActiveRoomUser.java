package adam.dev.liveCode.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@IdClass(RoomUserId.class)
@Table(name="active_room_user")
public class ActiveRoomUser {

    @Id
    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    @Id
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="joined_at")
    private LocalDateTime joinedAt;

}