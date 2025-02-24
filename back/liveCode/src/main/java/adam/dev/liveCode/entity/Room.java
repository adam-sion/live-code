package adam.dev.liveCode.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name="rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @JsonBackReference(value = "room-roomUsers")
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomUser> roomUsers = new ArrayList<>();

    @JsonManagedReference(value = "room-roomUserRequests")
    @OneToMany(mappedBy = "requestedRoom")
    private List<RoomUserRequest> roomUserRequests;

//    @OneToMany(mappedBy = "originalRoom")
//    private List<RoomCode> roomCodes;

    @Override
    public String toString() {
        return String.format("Room{ id: %s, name: %s}", id, name);
    }

}
