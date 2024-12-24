package adam.dev.liveCode.controller;

import adam.dev.liveCode.entity.CodeLineOperation;
import adam.dev.liveCode.service.CodeLineOperationService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class CodeLineOperationController {

    private CodeLineOperationService codeLineOperationService;

    public CodeLineOperationController(CodeLineOperationService codeLineOperationService) {
        this.codeLineOperationService = codeLineOperationService;
    }

    @MessageMapping("/sendCodeLineOperation")
    @SendTo("/room/{roomId}")
    public CodeLineOperation sendCodeLineOperation(CodeLineOperation codeLineOperation) {
        codeLineOperationService.handleCodeLineOperation(codeLineOperation);

        return codeLineOperation;
    }

}
