package adam.dev.liveCode.service;

import adam.dev.liveCode.entity.Room;
import adam.dev.liveCode.entity.RoomCode;
import adam.dev.liveCode.dao.RoomCodeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Table;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomCodeService {

    private RoomCodeRepository roomCodeRepository;

    private RoomService roomService;

    public RoomCodeService(RoomCodeRepository roomCodeRepository) {
        this.roomCodeRepository = roomCodeRepository;
    }


    public void createOrUpdateRoomCode(Long roomId, String language, String code) {
        Room room = roomService.findById(roomId);

        try {
           RoomCode roomCode = roomCodeRepository.findByOriginalRoomAndLanguage(room, language)
                   .orElseThrow(() -> new EntityNotFoundException(String.format("Room code: %s not found", roomId)));
           roomCode.setCode(code);
           roomCodeRepository.save(roomCode);
       } catch (EntityNotFoundException e) {
           RoomCode roomCode = new RoomCode(room, language, code);
           roomCodeRepository.save(roomCode);
       }
    }

}
