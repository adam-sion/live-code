package adam.dev.liveCode.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Data
@Entity
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

}
