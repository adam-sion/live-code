package adam.dev.liveCode.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="room_user_requests")
public class RoomUserRequest {

    public RoomUserRequest() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room requestedRoom;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User requestedUser;

    @Column
    private String status;

}
