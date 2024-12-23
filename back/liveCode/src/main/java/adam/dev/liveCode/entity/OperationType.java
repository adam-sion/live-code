package adam.dev.liveCode.entity;

public enum OperationType {

    UPDATE("update"),

    INSERT("insert"),

    DELETE("delete");

    private final String name;

    OperationType(String name) {
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
