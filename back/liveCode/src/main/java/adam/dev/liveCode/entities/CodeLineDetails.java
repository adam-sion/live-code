package adam.dev.liveCode.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "code_line_details")
public class CodeLineDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column(name = "line_number")
    private int lineNumber;

    @OneToOne(mappedBy = "codeLine")
    private List<CodeLine> codeLines;

}
