package tetrispvp.network.detail;

/**
 * Near end of a connection.
 */
public interface LocalEndpoint {
    /**
     * @return The unique endpoint id.
     * Allows multiple connections per application.
     */
    String getUID();
}
