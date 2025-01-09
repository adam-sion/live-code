package adam.dev.liveCode.security;

import adam.dev.liveCode.entity.Role;
import adam.dev.liveCode.service.RoomUserSecurityService;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

public class RoomUserPermissionEvaluator implements PermissionEvaluator {

    private final RoomUserSecurityService roomSecurityService;

    public RoomUserPermissionEvaluator(RoomUserSecurityService roomSecurityService) {
        this.roomSecurityService = roomSecurityService;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if ("Room".equals(targetType) && targetId instanceof Long) {
            String username = authentication.getName();
            return roomSecurityService.hasRoleAccessToRoom((Long) targetId, username, Role.USER);
        }
        return false;
    }

}
