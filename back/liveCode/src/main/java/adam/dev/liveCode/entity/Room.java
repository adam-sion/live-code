package adam.dev.liveCode.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name="rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "room")
    private List<RoomUser> roomUsers;

    @OneToMany(mappedBy = "requestedRoom")
    private List<RoomUserRequest> roomUserRequests;

    @OneToOne(mappedBy = "originalRoom")
    private RoomCode roomCode;

}
