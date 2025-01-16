package adam.dev.liveCode.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name="room_user")
public class RoomUser {

   @EmbeddedId
   private RoomUserId id;

    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private Room room;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "last_active_at")
    private LocalDateTime lastActiveAt;

    public User getUser() {
     return user;
    }

}

