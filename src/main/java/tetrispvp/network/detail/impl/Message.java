package tetrispvp.network.detail.impl;

import java.io.Serializable;

public class Message implements Serializable {
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
