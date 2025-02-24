package adam.dev.liveCode.service;

import adam.dev.liveCode.dao.RoomUserRepository;
import adam.dev.liveCode.entity.RoomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomUserService {

    private final RoomUserRepository roomUserRepository;

    public void addRoomUser(RoomUser roomUser) {
        roomUserRepository.save(roomUser);
    }

}
