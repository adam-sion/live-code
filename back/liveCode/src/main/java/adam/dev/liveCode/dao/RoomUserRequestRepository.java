package adam.dev.liveCode.dao;

import adam.dev.liveCode.entity.RoomUserRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomUserRequestRepository extends JpaRepository<RoomUserRequest, Long> { }
