package adam.dev.liveCode.controller;

import adam.dev.liveCode.entity.RoomCode;
import adam.dev.liveCode.service.RoomCodeService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class CodeController {

    private RoomCodeService roomCodeService;

    public CodeController(RoomCodeService roomCodeService) {
        this.roomCodeService = roomCodeService;
    }

    @MessageMapping("/sendCodeLineOperation")
    @SendTo("topic/roomCode/{roomCodeId}")
    public String sendCodeLineOperation(@DestinationVariable Long roomCodeId, String code) {
        roomCodeService.updateCode(roomCodeId, code);

        return code;
    }

}
