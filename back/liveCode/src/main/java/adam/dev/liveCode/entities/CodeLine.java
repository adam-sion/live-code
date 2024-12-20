package adam.dev.liveCode.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "code_line")
public class CodeLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="room_code_id")
    private RoomCode roomCode;

    @ManyToMany
    @JoinColumn(name="user_id")
    private User codeLineWriter;

    @OneToOne
    @JoinColumn(name="code_line_details_id")
    private CodeLineDetails codeLineDetails;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @PreUpdate
    private void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

}
