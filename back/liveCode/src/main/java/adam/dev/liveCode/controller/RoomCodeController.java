package adam.dev.liveCode.controller;

import adam.dev.liveCode.service.RoomCodeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/roomCode")
public class RoomCodeController {

    private final RoomCodeService roomCodeService;

    @GetMapping("/{roomName}/{language}/code")
    public String getCode(@PathVariable String roomName, @PathVariable String language) {
        return roomCodeService.getRoomCode(roomName, language).getCode();
    }

}
