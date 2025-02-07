package adam.dev.liveCode.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomUser> roomUsers = new ArrayList<>();

    @OneToMany(mappedBy = "requestedRoom")
    private List<RoomUserRequest> roomUserRequests;

//    @OneToMany(mappedBy = "originalRoom")
//    private List<RoomCode> roomCodes;

}
