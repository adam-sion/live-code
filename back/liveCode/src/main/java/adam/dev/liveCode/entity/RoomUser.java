package adam.dev.liveCode.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@IdClass(RoomUserId.class)
@Table(name="room_user")
public class RoomUser {

   @Id
   private Long roomId;

   @Id
   private Long userId;


    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

}

