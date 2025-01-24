package adam.dev.liveCode.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum Role {

    USER("user"),

     ADMIN("admin");

    private final String name;

}
