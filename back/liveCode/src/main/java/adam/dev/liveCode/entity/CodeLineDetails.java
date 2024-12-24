package adam.dev.liveCode.entity;

import jakarta.persistence.*;

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

    @OneToOne(mappedBy = "codeLineDetails")
    private CodeLine codeLine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public CodeLine getCodeLine() {
        return codeLine;
    }

    public void setCodeLine(CodeLine codeLine) {
        this.codeLine = codeLine;
    }
}
