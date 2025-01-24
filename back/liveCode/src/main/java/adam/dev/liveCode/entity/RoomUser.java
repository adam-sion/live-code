package adam.dev.liveCode.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Data
@Table(name="room_user")
public class RoomUser {

   @EmbeddedId
   private RoomUserId id;

 @JsonBackReference
    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private Room room;

 @JsonBackReference
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_active")
    private boolean isActive;

}

