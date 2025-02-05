package adam.dev.liveCode.controller;

import adam.dev.liveCode.service.RoomCodeService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class CodeController {

    private final RoomCodeService roomCodeService;

    @MessageMapping("/sendCodeLineOperation")
    @SendTo("topic/roomCode/{roomId}/{language}")
    public String sendCodeLineOperation(@DestinationVariable Long roomId, @DestinationVariable  String language, String code) {
        roomCodeService.createOrUpdateRoomCode(roomId, language, code);

        return code;
    }

}
