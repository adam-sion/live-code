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

    @Column(name = "line_number")
    private int lineNumber;

    @Column(name = "new_content")
    private String newContent;

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public User getEditor() {
        return editor;
    }

    public void setEditor(User editor) {
        this.editor = editor;
    }

    public CodeLine getCodeLine() {
        return codeLine;
    }

    public void setCodeLine(CodeLine codeLine) {
        this.codeLine = codeLine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public String getNewContent() {
        return newContent;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }
}
