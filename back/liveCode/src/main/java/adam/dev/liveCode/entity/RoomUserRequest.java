package adam.dev.liveCode.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name="room_user_requests")
public class RoomUserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference(value = "room-roomUserRequests")
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room requestedRoom;

    @JsonBackReference(value = "user-roomUserRequests")
    @ManyToOne
    @JoinColumn(name="user_id")
    private User requestedUser;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    @Override
    public String toString() {
        return String.format("RoomUserRequest{ id: %s, status: %s }", id, status);
    }

}
