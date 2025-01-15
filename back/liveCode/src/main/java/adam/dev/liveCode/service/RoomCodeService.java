package adam.dev.liveCode.service;

import adam.dev.liveCode.entity.RoomCode;
import adam.dev.liveCode.dao.RoomCodeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Table;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void updateCode(Long id, String code) {
        RoomCode roomCode = getRoomCodeById(id);
        roomCode.setCode(code);
        roomCodeRepository.save(roomCode);
    }

}
