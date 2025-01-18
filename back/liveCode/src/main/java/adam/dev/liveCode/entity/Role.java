package adam.dev.liveCode.entity;

public enum Role {

    USER("user"),

     ADMIN("admin");

    private final String name;

     Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
