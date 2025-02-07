package adam.dev.liveCode.service;

import adam.dev.liveCode.dao.RoomCodeRepository;
import adam.dev.liveCode.entity.Room;
import adam.dev.liveCode.entity.RoomCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoomCodeService {

    private final RoomCodeRepository roomCodeRepository;

    private final RoomService roomService;


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
