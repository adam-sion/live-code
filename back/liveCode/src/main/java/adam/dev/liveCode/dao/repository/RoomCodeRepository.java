package adam.dev.liveCode.dao.repository;

import adam.dev.liveCode.entity.RoomCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomCodeRepository extends JpaRepository<RoomCode, Long> {
}
