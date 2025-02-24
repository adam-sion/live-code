package adam.dev.liveCode.controller;
import adam.dev.liveCode.dto.JoinRoomDTO;
import adam.dev.liveCode.dto.RoomUserRequestDTO;
import adam.dev.liveCode.entity.RoomUser;
import adam.dev.liveCode.entity.RoomUserRequest;
import adam.dev.liveCode.service.RoomUserRequestService;
import adam.dev.liveCode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/roomUserRequest")
public class RoomUserRequestController {

    private final RoomUserRequestService roomUserRequestService;

    @PostMapping("/create")
    public void createRoomUserRequest(@RequestBody JoinRoomDTO joinRoomDTO) {
      roomUserRequestService.createRoomUserRequest(joinRoomDTO);
    }

    @PostMapping("/handle")
    public void handleRoomUserRequest(@RequestBody RoomUserRequestDTO roomUserRequestDTO) {
        System.out.println("recieved request " + roomUserRequestDTO);
        roomUserRequestService.handleRoomUserRequest(roomUserRequestDTO);
    }

    @GetMapping("/{adminId}")
    public List<RoomUserRequest> getRoomUserRequests(@PathVariable Long adminId) {
       List<RoomUserRequest> reqs =  roomUserRequestService.getAllRoomUserRequests(adminId);
        System.out.println(reqs);
        return reqs;
    }

}
