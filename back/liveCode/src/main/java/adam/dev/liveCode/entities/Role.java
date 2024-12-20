package adam.dev.liveCode.entities;

public enum Role {

    USER("user"),

     ADMIN("admin");

    private final String name;

     Role(String name) {
        this.name = name;
    }

    public String getName() {
         return this.name;
    }

}
