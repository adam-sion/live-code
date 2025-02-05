package adam.dev.liveCode.dao;

import adam.dev.liveCode.entity.Room;
import adam.dev.liveCode.entity.RoomCode;
import adam.dev.liveCode.entity.RoomCodeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomCodeRepository extends JpaRepository<RoomCode, RoomCodeId> {

    Optional<RoomCode> findByOriginalRoomAndLanguage(Room originalRoom, String language);

}
