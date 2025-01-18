package adam.dev.liveCode.service;

import adam.dev.liveCode.dao.RoomRepository;
import adam.dev.liveCode.dto.CreateRoomDTO;
import adam.dev.liveCode.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    private UserService userService;

    public RoomService(RoomRepository roomRepository, UserService userService) {
        this.roomRepository = roomRepository;
        this.userService = userService;
    }

    @Transactional
    public Room add(CreateRoomDTO roomDTO, String userName) {
        Room room = new Room();
        room.setName(roomDTO.getRoomName());
        Room savedRoom = roomRepository.save(room);
        User admin = userService.findByUsername(userName);
        RoomUser adminRoomUser = new RoomUser();
        RoomUserId roomUserId = new RoomUserId(savedRoom.getId(), admin.getId());
        adminRoomUser.setId(roomUserId);
        adminRoomUser.setRoom(savedRoom);
        adminRoomUser.setUser(admin);
        adminRoomUser.setRole(Role.ADMIN);
        savedRoom.getRoomUsers().add(adminRoomUser);
        return savedRoom;
    }


}
