package adam.dev.liveCode.dto;

import adam.dev.liveCode.entity.Role;

public class RoomUserDTO {
    private Role role;
    private boolean isActive;
    private String username;

    public RoomUserDTO(Role role, boolean isActive, String username) {
        this.role = role;
        this.isActive = isActive;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
