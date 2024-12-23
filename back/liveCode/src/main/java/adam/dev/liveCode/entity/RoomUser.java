package adam.dev.liveCode.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="room_user")
public class RoomUser {

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

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

}

