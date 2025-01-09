package adam.dev.liveCode.service;

import adam.dev.liveCode.entity.RoomCode;
import adam.dev.liveCode.dao.RoomCodeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoomCodeService {

    private RoomCodeRepository roomCodeRepository;

    public RoomCodeService(RoomCodeRepository roomCodeRepository) {
        this.roomCodeRepository = roomCodeRepository;
    }

    public RoomCode getRoomCodeById(Long id) {
        return roomCodeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Room code: %s not found", id)));
    }

}
