package adam.dev.liveCode.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="rooms")
public class Room {

    public Room() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomUser> roomUsers = new ArrayList<>();

    @OneToMany(mappedBy = "requestedRoom")
    private List<RoomUserRequest> roomUserRequests = new ArrayList<>();

    @OneToOne(mappedBy = "originalRoom")
    private RoomCode roomCode;

    public List<RoomUser> getRoomUsers() {
        return roomUsers;
    }

    public void setName(String name) {
        this.name = name;
    }
}
