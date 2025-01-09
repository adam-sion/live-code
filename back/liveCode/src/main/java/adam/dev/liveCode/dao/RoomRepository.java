package adam.dev.liveCode.dao;

import adam.dev.liveCode.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> { }
