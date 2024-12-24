package adam.dev.liveCode.service;

import adam.dev.liveCode.entity.CodeLine;
import adam.dev.liveCode.repository.CodeLineRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CodeLineService {

    private CodeLineRepository codeLineRepository;

    public CodeLineService(CodeLineRepository codeLineRepository) {
        this.codeLineRepository = codeLineRepository;
    }

    public CodeLine getCodeLineById(Long id) {
        return codeLineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Code line: %s not found", id)));
    }

    @Transactional
    public void createCodeLine(CodeLine codeLine) {
        Long roomCodeId = codeLine.getRoomCode().getId();
        int newLineNumber = codeLine.getCodeLineDetails().getLineNumber();
        codeLineRepository.updateLineNumbersAfter(newLineNumber, roomCodeId, 1);
        codeLineRepository.save(codeLine);
    }

    @Transactional
    public void deleteCodeLine(CodeLine codeLine) {
        Long roomCodeId = codeLine.getRoomCode().getId();
        int newLineNumber = codeLine.getCodeLineDetails().getLineNumber();
        codeLineRepository.updateLineNumbersAfter(newLineNumber, roomCodeId, -1);
        codeLineRepository.delete(codeLine);
    }

}
