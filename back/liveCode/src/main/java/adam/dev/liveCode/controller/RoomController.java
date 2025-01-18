package adam.dev.liveCode.controller;

import adam.dev.liveCode.dto.CreateRoomDTO;
import adam.dev.liveCode.entity.Room;
import adam.dev.liveCode.service.RoomService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public Room addRoom(@RequestBody CreateRoomDTO room, @AuthenticationPrincipal UserDetails userDetails) {
       return roomService.add(room, userDetails.getUsername());
    }

}
