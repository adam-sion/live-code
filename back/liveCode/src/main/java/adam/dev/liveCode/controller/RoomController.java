package adam.dev.liveCode.controller;

import adam.dev.liveCode.dto.CreateRoomDTO;
import adam.dev.liveCode.dto.RoomDTO;
import adam.dev.liveCode.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public RoomDTO addRoom(@RequestBody CreateRoomDTO room, @AuthenticationPrincipal UserDetails userDetails) {
       return roomService.add(room, userDetails.getUsername());
    }

}
