package adam.dev.liveCode.dao;


import adam.dev.liveCode.entity.Room;
import adam.dev.liveCode.entity.RoomUser;
import adam.dev.liveCode.entity.RoomUserId;
import adam.dev.liveCode.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomUserRepository extends JpaRepository<RoomUser, RoomUserId> {
    List<RoomUser> findAllByUser(User user);

    List<RoomUser> findAllByRoom(Room room);
}
