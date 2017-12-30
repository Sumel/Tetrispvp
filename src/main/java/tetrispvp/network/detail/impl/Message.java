package tetrispvp.network.detail.impl;

import java.util.List;

public class Message {
    Message(String str) {
        throw new IllegalStateException("Not implemented.");
    }

    Message(List<String> modules, String name, Object with) {
        throw new IllegalStateException("Not implemented.");
    }

    /**
     * @return List of nested modules this message is for.
     */
    List<String> getModules() {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public String toString() {
        throw new IllegalStateException("Not implemented.");
    }
}
