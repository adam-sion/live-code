package adam.dev.liveCode.service;

import adam.dev.liveCode.dao.RoomUserRequestRepository;
import adam.dev.liveCode.dto.JoinRoomDTO;
import adam.dev.liveCode.dto.RoomUserRequestDTO;
import adam.dev.liveCode.entity.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoomUserRequestService {

    private final RoomUserRequestRepository roomUserRequestRepository;

    private final RoomService roomService;

    private final UserService userService;

    private final RoomUserService roomUserService;

    public void createRoomUserRequest(JoinRoomDTO joinRoomDTO) {
        RoomUserRequest roomUserRequest = new RoomUserRequest();
        User user = userService.findById(joinRoomDTO.getUserId());
        Room room = roomService.findByName(joinRoomDTO.getRoomName());
        roomUserRequest.setRequestedUser(user);
        roomUserRequest.setRequestedRoom(room);
        roomUserRequest.setStatus(Status.pending);
        roomUserRequestRepository.save(roomUserRequest);
    }

    public List<RoomUserRequest> getAllRoomUserRequests(Long adminId) {
        return userService.findById(adminId)
                .getRoomUsers().stream()
                .flatMap(roomUser -> roomUser.getRoom().getRoomUserRequests().stream())
                .filter(roomUserRequest -> roomUserRequest.getStatus() == Status.pending)
                .collect(Collectors.toList());
    }

    @Transactional
    public RoomUserRequest handleRoomUserRequest(RoomUserRequestDTO roomUserRequestDTO) {
        Long roomUserRequestId = roomUserRequestDTO.getRoomUserRequestId();
        Status status = roomUserRequestDTO.getStatus();
        RoomUserRequest roomUserRequest = roomUserRequestRepository.findById(roomUserRequestId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("RoomUserRequest with id %s not found", roomUserRequestId)));
       if (roomUserRequest.getStatus() != Status.pending) {
           System.out.println("already pending");
       throw new IllegalArgumentException(String.format("RoomUserRequest with id %s is not pending", roomUserRequestId));
       }

        roomUserRequest.setStatus(status);

        switch (status) {
            case accepted:
                RoomUser roomUser = new RoomUser();
                roomUser.setRoom(roomUserRequest.getRequestedRoom());
                roomUser.setUser(roomUserRequest.getRequestedUser());
                roomUser.setRole(Role.USER);
                RoomUserId roomUserId = new RoomUserId(roomUserRequest.getRequestedRoom().getId(), roomUserRequest.getRequestedUser().getId());
                roomUser.setId(roomUserId);
                roomUser.setActive(false);
                roomUserService.addRoomUser(roomUser);
                break;

            case declined:
                break;

            default:
                throw new IllegalArgumentException("Invalid handle roomUserRequest status");
        }

        return roomUserRequestRepository.save(roomUserRequest);
    }

}
