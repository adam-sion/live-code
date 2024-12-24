package adam.dev.liveCode.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "code_line")
public class CodeLine {

    public CodeLine() {
    }

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

    public CodeLine(RoomCode roomCode, CodeLineDetails codeLineDetails) {
        this.roomCode = roomCode;
        this.codeLineDetails = codeLineDetails;
    }

    @PreUpdate
    private void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomCode getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(RoomCode roomCode) {
        this.roomCode = roomCode;
    }

    public CodeLineDetails getCodeLineDetails() {
        return codeLineDetails;
    }

    public void setCodeLineDetails(CodeLineDetails codeLineDetails) {
        this.codeLineDetails = codeLineDetails;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
