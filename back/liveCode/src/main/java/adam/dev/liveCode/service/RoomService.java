package adam.dev.liveCode.service;

import adam.dev.liveCode.dao.RoomRepository;
import adam.dev.liveCode.dto.CreateRoomDTO;
import adam.dev.liveCode.dto.RoomDTO;
import adam.dev.liveCode.dto.RoomUserDTO;
import adam.dev.liveCode.entity.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    private UserService userService;

    public RoomService(RoomRepository roomRepository, UserService userService) {
        this.roomRepository = roomRepository;
        this.userService = userService;
    }

    @Transactional
    public RoomDTO add(CreateRoomDTO roomDTO, String userName) {
        Room room = new Room();
        room.setName(roomDTO.getRoomName());
        Room savedRoom = roomRepository.save(room);
        User admin = userService.findByUsername(userName);
        RoomUser adminRoomUser = new RoomUser();
        RoomUserId roomUserId = new RoomUserId(savedRoom.getId(), admin.getId());
        adminRoomUser.setId(roomUserId);
        adminRoomUser.setActive(false);
        adminRoomUser.setRoom(savedRoom);
        adminRoomUser.setUser(admin);
        adminRoomUser.setRole(Role.ADMIN);
        savedRoom.getRoomUsers().add(adminRoomUser);
        RoomDTO toReturn = new RoomDTO();
        toReturn.setRoomName(savedRoom.getName());
        toReturn.setRooms(savedRoom.getRoomUsers().stream()
                .map((roomUser -> new RoomUserDTO(roomUser.getRole(), roomUser.isActive(), roomUser.getUser().getUsername())))
        .collect(Collectors.toList()));

        return toReturn;
    }

    public Room findByName(String name) {
        return roomRepository.findByName(name)
                .orElseThrow(()->  new EntityNotFoundException("Room not found"));
    }

    public void deleteRoom(String roomName) {
        Room room = findByName(roomName);
        roomRepository.delete(room);
    }

}
