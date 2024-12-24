package adam.dev.liveCode.service;

import adam.dev.liveCode.entity.CodeLine;
import adam.dev.liveCode.entity.CodeLineDetails;
import adam.dev.liveCode.entity.CodeLineOperation;
import adam.dev.liveCode.repository.CodeLineRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CodeLineOperationService {

    private final CodeLineRepository codeLineRepository;

    private RoomCodeService roomCodeService;

    private CodeLineService codeLineService;

    private CodeLineDetailsService codeLineDetailsService;

    public CodeLineOperationService(RoomCodeService roomCodeService, CodeLineService codeLineService, CodeLineRepository codeLineRepository) {
        this.roomCodeService = roomCodeService;
        this.codeLineService = codeLineService;
        this.codeLineRepository = codeLineRepository;
    }

    @Transactional
    public void handleCodeLineOperation(CodeLineOperation operation) {
        //save

        CodeLine codeLine = new CodeLine();
        CodeLineDetails codeLineDetails = new CodeLineDetails();

        switch (operation.getOperationType()) {
            case INSERT:

                codeLineDetails.setContent(operation.getNewContent());
                codeLineDetails.setLineNumber(operation.getLineNumber());
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

}
