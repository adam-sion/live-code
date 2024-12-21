package adam.dev.liveCode.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@IdClass(RoomUserId.class)
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