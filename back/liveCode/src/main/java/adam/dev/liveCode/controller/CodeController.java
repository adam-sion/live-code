package adam.dev.liveCode.controller;

import adam.dev.liveCode.dto.CompileCodeDTO;
import adam.dev.liveCode.dto.JDoodleResponseDTO;
import adam.dev.liveCode.dto.RoomCodeDTO;
import adam.dev.liveCode.service.CompilerService;
import adam.dev.liveCode.service.RoomCodeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CodeController {

    private final RoomCodeService roomCodeService;
    private final CompilerService compilerService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/sendCodeLineOperation")
    public void sendCodeLineOperation(RoomCodeDTO message) {
        System.out.println(message);
        roomCodeService.createOrUpdateRoomCode(
                message.getRoomName(),
                message.getLanguage(),
                message.getCode()
        );

        String destination = String.format("/topic/roomCode/%s/%s", message.getRoomName(), message.getLanguage());
        messagingTemplate.convertAndSend(destination, message);
    }

    @PostMapping("/compile")
   public ResponseEntity<JDoodleResponseDTO> compile(@RequestBody CompileCodeDTO compileCodeDTO) {
      return ResponseEntity.ok(compilerService.compile(compileCodeDTO));
    }

}

