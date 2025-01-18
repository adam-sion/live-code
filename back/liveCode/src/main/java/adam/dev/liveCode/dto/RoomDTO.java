package adam.dev.liveCode.dto;

import java.util.List;

public class RoomDTO {
    private String roomName;
    private List<RoomUserDTO> rooms;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<RoomUserDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomUserDTO> rooms) {
        this.rooms = rooms;
    }
}
