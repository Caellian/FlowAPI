package hr.caellian.flow.network;

import java.util.Map;

/**
 * @author caellian
 *         Created on 20/07/16 at 15:08 CET.
 */
public interface NetworkComponent {
    Network getNetwork();

    boolean isCompatibleWithNetwork(Network networkClass);

    Network setNetwork(Network network);

    Network createDefaultNetwork();

    void networkNotFound();

    Object getPosition();

    Map<Object, NetworkComponent> getNeighbours();

    boolean addNeighbour(Object position, NetworkComponent neighbour);

    boolean removeNeighbour(Object position);

    default boolean removeNeighbour(NetworkComponent neighbour) {
        for (Map.Entry<Object, NetworkComponent> entry : getNeighbours().entrySet()) {
            if (entry.getValue() == neighbour) {
                return removeNeighbour(entry.getKey());
            }
        }
        return false;
    }
}
