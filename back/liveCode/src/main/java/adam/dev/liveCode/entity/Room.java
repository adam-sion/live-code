package adam.dev.liveCode.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


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
    private Set<RoomUser> roomUsers;

    @OneToMany(mappedBy = "requestedRoom")
    private Set<RoomUserRequest> roomUserRequests;

    @OneToOne(mappedBy = "originalRoom")
    private RoomCode roomCode;

}
