package adam.dev.liveCode.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "code_line")
public class CodeLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="room_code_id")
    private RoomCode roomCode;

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
