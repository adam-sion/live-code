package adam.dev.liveCode.service;

import adam.dev.liveCode.dao.RoomRepository;
import adam.dev.liveCode.entity.Room;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void add(Room room) {
        roomRepository.save(room);
    }

}
