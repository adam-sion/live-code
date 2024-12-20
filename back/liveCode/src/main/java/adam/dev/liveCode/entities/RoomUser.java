package adam.dev.liveCode.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@IdClass(RoomUserId.class)
@Table(name="room_user")
public class RoomUser {

    @Id
    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    @Id
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

}

