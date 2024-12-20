package adam.dev.liveCode.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<RoomUser> roomUsers;

    @OneToMany(mappedBy = "requestedUser")
    private Set<RoomUserRequest> roomUserRequests;

    @OneToMany(mappedBy = "codeLineWriter")
    private List<CodeLine> codeLines;

}
