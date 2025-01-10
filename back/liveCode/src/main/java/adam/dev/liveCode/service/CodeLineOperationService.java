package adam.dev.liveCode.service;

import adam.dev.liveCode.entity.CodeLine;
import adam.dev.liveCode.entity.CodeLineDetails;
import adam.dev.liveCode.entity.CodeLineOperation;
import adam.dev.liveCode.dao.CodeLineOperationRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CodeLineOperationService {

    private final CodeLineOperationRepository codeLineOperationRepository;

    private CodeLineService codeLineService;

    private CodeLineDetailsService codeLineDetailsService;

    public CodeLineOperationService(CodeLineOperationRepository codeLineOperationRepository, CodeLineService codeLineService, CodeLineDetailsService codeLineDetailsService) {
        this.codeLineOperationRepository = codeLineOperationRepository;
        this.codeLineService = codeLineService;
        this.codeLineDetailsService = codeLineDetailsService;
    }


    @Transactional
    public void handleCodeLineOperation(CodeLineOperation operation) {
        createCodeLineOperation(operation);
        CodeLineDetails codeLineDetails = new CodeLineDetails();

        switch (operation.getOperationType()) {
            case INSERT:

                codeLineDetails.setContent(operation.getNewContent());
                codeLineDetails.setLineNumber(operation.getLineNumber());
                CodeLine codeLine = new CodeLine();
                codeLine.setCodeLineDetails(codeLineDetails);
                codeLine.setRoomCode(operation.getCodeLine().getRoomCode());
                codeLine.setCodeLineDetails(codeLineDetails);
                codeLineService.createCodeLine(codeLine);

                break;
            case UPDATE:
                codeLineDetails = codeLineService.getCodeLineById(operation.getCodeLine().getId()).getCodeLineDetails();
                codeLineDetailsService.updateContent(operation.getNewContent(), codeLineDetails.getId());
                break;
            case DELETE:
                codeLineService.deleteCodeLine(operation.getCodeLine());
                break;
            default:
                throw new IllegalArgumentException("Unknown operation type");
        }
    }

    @Transactional
    public void createCodeLineOperation(CodeLineOperation codeLineOperation) {
        try {
            codeLineOperationRepository.save(codeLineOperation);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException("Code line operation already exists");
        }
    }

}
