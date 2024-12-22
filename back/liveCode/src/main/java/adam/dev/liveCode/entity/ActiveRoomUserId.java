package adam.dev.liveCode.entity;

import jakarta.persistence.Access;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class ActiveRoomUserId implements Serializable {

    private Long roomId;
    private Long userId;

    public ActiveRoomUserId() {}

}
