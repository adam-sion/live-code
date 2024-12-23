package adam.dev.liveCode.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "code_line_operations")
public class CodeLineOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "code_line_id")
    private CodeLine codeLine;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User editor;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    private OperationType operationType;

    @Column(name = "new_content")
    private String newContent;

}
