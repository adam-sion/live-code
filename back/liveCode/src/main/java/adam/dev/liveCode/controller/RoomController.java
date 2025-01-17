package adam.dev.liveCode.controller;

import adam.dev.liveCode.entity.Room;
import adam.dev.liveCode.service.RoomService;
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
    public String addRoom(@RequestBody Room room) {
        roomService.add(room);

        return "room added successfully";
    }

}
