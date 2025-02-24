package adam.dev.liveCode.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RoomUserId implements Serializable {

    private Long roomId;

    private Long userId;

}
