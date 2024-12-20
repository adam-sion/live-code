package adam.dev.liveCode.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="room_code")
public class RoomCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room originalRoom;

    @Column
    private String language;

    @OneToMany(mappedBy = "roomCode")
    private List<CodeLine> codeLines;

}
