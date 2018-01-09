package tetrispvp.network;

import tetrispvp.network.detail.impl.ConcreteNetworkModule;

/**
 * Provides a new network module.
 */
public class NetworkModuleFactory {
    static NetworkModule getNetworkModule() {
        return new ConcreteNetworkModule();
    }
}
