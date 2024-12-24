package adam.dev.liveCode.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="room_code")
public class RoomCode {

    public RoomCode() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room originalRoom;

    @Column
    private String language;

    @OneToMany(mappedBy = "roomCode")
    private Set<CodeLine> codeLines;

    public Room getOriginalRoom() {
        return originalRoom;
    }

    public void setOriginalRoom(Room originalRoom) {
        this.originalRoom = originalRoom;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Set<CodeLine> getCodeLines() {
        return codeLines;
    }

    public void setCodeLines(Set<CodeLine> codeLines) {
        this.codeLines = codeLines;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
