package tetrispvp.network;

import tetrispvp.network.detail.impl.ConcreteNetworkModule;

/**
 * Provides a new network module.
 */
public class NetworkModuleFactory {
    private static ConcreteNetworkModule last;

    public static NetworkModule getNetworkModule() {
        last = new ConcreteNetworkModule();
        return last;
    }

    public static NetworkModule last() {
        return last;
    }
}
