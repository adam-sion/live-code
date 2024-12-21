package adam.dev.liveCode.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
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
