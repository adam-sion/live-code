package adam.dev.liveCode.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Username cannot be null")
    @Column
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password cannot be null")
    @Column
    private String password;

    @NotNull(message = "Email cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Invalid email format")
    @Column
    private String email;

    @JsonManagedReference(value = "user-roomUsers")
    @OneToMany(mappedBy = "user")
    private List<RoomUser> roomUsers;

    @JsonManagedReference(value = "user-roomUserRequests")
    @OneToMany(mappedBy = "requestedUser")
    private List<RoomUserRequest> roomUserRequests;

    @Override
    public String toString() {
        return String.format("User{ id: %s, username: %s, password: %s, email: %s, roomUsers: %s, roomUserRequests: %s}", id, username, password, email, roomUsers, roomUserRequests);
    }

}
