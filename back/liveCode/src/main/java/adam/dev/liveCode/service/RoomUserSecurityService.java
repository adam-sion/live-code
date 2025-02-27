package adam.dev.liveCode.service;

import adam.dev.liveCode.dao.RoomUserRepository;
import adam.dev.liveCode.dao.UserRepository;
import adam.dev.liveCode.entity.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomUserSecurityService {

    private final RoomUserRepository roomUserRepository;

    private final UserRepository userRepository;

    public boolean hasRoleAccessToRoom(Long roomId, String username, Role role) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        RoomUser roomUser = roomUserRepository.findById((new RoomUserId(roomId, user.getId())))
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        return roomUser.getRole().equals(role);
    }

}
