package tetrispvp.network.detail.impl;

public class Message {
    private final String name;
    private final Object with;

    Message(String name, Object with) {
        this.name = name;
        this.with = with;
    }

    public String name() {
        return name;
    }

    public Object with() {
        return with;
    }
}
