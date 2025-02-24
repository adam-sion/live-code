package adam.dev.liveCode.controller;

import adam.dev.liveCode.dto.CreateRoomDTO;
import adam.dev.liveCode.dto.JoinRoomDTO;
import adam.dev.liveCode.dto.RoomDTO;
import adam.dev.liveCode.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public ResponseEntity<RoomDTO> addRoom(@RequestBody CreateRoomDTO room, @AuthenticationPrincipal UserDetails userDetails) {
        RoomDTO roomDTO = roomService.add(room, userDetails.getUsername());

        return new ResponseEntity<>(roomDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{roomName}")
    public ResponseEntity<Void> deleteRoom(@PathVariable String roomName) {
        roomService.deleteRoom(roomName);

        return ResponseEntity.noContent()
                .build();
    }


    @MessageMapping("/joinRoom")
    public void joinRoom(JoinRoomDTO joinRoomDTO) {
        String destination = String.format("/topic/%s/joinRoom", joinRoomDTO.getRoomName());
        messagingTemplate.convertAndSend(destination, joinRoomDTO);
    }

}
