package tetrispvp.network;

import tetrispvp.network.detail.impl.ConcreteNetworkModule;

/**
 * Provides a new network module.
 */
public class NetworkModuleFactory {
    public static NetworkModule getNetworkModule() {
        return new ConcreteNetworkModule();
    }
}
