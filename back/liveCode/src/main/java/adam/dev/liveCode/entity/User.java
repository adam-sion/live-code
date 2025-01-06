package adam.dev.liveCode.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.util.List;
import java.util.Set;


@Getter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Username cannot be null")
    @Column
    private String username;

    @JsonIgnore
    @NotNull(message = "Password cannot be null")
    @Column
    private String password;

    @NotNull(message = "Email cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Invalid email format")
    @Column
    private String email;

    @OneToMany(mappedBy = "user")
    private List<RoomUser> roomUsers;

    @OneToMany(mappedBy = "requestedUser")
    private List<RoomUserRequest> roomUserRequests;

    @OneToMany(mappedBy = "editor")
    private List<CodeLineOperation> codeLineOperations;

    public User() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return this.id;
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
