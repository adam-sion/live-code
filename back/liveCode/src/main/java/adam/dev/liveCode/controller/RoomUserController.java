package adam.dev.liveCode.controller;

import adam.dev.liveCode.dao.RoomUserRepository;
import adam.dev.liveCode.entity.RoomUser;
import adam.dev.liveCode.entity.RoomUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roomUser")
public class RoomUserController {

    private final RoomUserRepository roomUserRepository;

    @PatchMapping("/active/{active}")
    public void setActive(@RequestBody RoomUserId roomUserId, @PathVariable boolean active) {
        RoomUser roomUser = roomUserRepository.findById(roomUserId)
                .orElseThrow(() -> new RuntimeException("Room User Not Found"));
        roomUser.setActive(active);
        roomUserRepository.save(roomUser);
    }

}
