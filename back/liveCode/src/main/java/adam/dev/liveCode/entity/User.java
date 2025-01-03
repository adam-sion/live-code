package adam.dev.liveCode.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Set;


@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Username cannot be null")
    @Column
    private String username;

    @NotNull(message = "Password cannot be null")
    @Column
    private String password;

    @NotNull(message = "Email cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Invalid email format")
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
