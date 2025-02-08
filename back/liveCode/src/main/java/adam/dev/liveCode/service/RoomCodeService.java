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


    public void createOrUpdateRoomCode(String roomName, String language, String code) {
        Room room = roomService.findByName(roomName);

        try {
           RoomCode roomCode = roomCodeRepository.findByOriginalRoomAndLanguage(room, language)
                   .orElseThrow(() -> new EntityNotFoundException(String.format("Room code: %s not found", roomName)));
           roomCode.setCode(code);
           roomCodeRepository.save(roomCode);
       } catch (EntityNotFoundException e) {
           RoomCode roomCode = new RoomCode(room, language, code);
           roomCodeRepository.save(roomCode);
       }
    }

}
