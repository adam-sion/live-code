package adam.dev.liveCode.controller;

import adam.dev.liveCode.dto.RoomCodeDTO;
import adam.dev.liveCode.service.RoomCodeService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class CodeController {

    private final RoomCodeService roomCodeService;
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
}

