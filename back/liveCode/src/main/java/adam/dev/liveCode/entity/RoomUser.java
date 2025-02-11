package adam.dev.liveCode.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference(value = "2")
    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private Room room;

    @JsonBackReference(value = "1")
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

