package adam.dev.liveCode.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @OneToMany(mappedBy = "originalRoom")
    private List<RoomCode> roomCodes = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<RoomUser> getRoomUsers() {
        return roomUsers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
}
