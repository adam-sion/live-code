package adam.dev.liveCode.entity;

import jakarta.persistence.*;

import java.util.Set;


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

    @OneToMany(mappedBy = "editor")
    private Set<CodeLineOperation> codeLineOperations;

    public User() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
