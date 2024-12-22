package adam.dev.liveCode.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@IdClass(ActiveRoomUserId.class)
@Table(name="active_room_user")
public class ActiveRoomUser {

    @Id
    Long roomId;

    @Id
    Long userId;

    @ManyToOne
    @JoinColumn(name="roomId")
    private Room room;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="joined_at")
    private LocalDateTime joinedAt;

}