package adam.dev.liveCode.controller;

import adam.dev.liveCode.dao.RoomUserRepository;
import adam.dev.liveCode.entity.RoomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roomUser")
public class RoomUserController {

    private final RoomUserRepository roomUserRepository;

    @PatchMapping("/active/{active}")
    public void setActive(@RequestBody RoomUser roomUser, @PathVariable boolean active) {
        roomUser.setActive(active);
        roomUserRepository.save(roomUser);
    }
}
