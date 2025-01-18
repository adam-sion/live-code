package adam.dev.liveCode.service;

import adam.dev.liveCode.dao.RoomRepository;
import adam.dev.liveCode.entity.Role;
import adam.dev.liveCode.entity.Room;
import adam.dev.liveCode.entity.RoomUser;
import adam.dev.liveCode.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    private UserService userService;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional
    public void add(String roomName, String userName) {
        Room room = new Room();
        User admin = userService.findByUsername(userName);
        RoomUser adminRoomUser = new RoomUser();
        adminRoomUser.setRoom(room);
        adminRoomUser.setUser(admin);
        adminRoomUser.setRole(Role.ADMIN);
        room.getRoomUsers().add(adminRoomUser);
        room.setName(roomName);
        roomRepository.save(room);
    }

}
