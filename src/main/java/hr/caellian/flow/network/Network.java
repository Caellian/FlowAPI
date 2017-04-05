package hr.caellian.flow.network;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author caellian
 *         Created on 20/07/16 at 15:05 CET.
 */
public interface Network {
    boolean canAddComponent(NetworkComponent toAdd);

    boolean addNetworkComponent(NetworkComponent toRegister);

    boolean removeNetworkComponent(NetworkComponent toRemove);

    boolean loadNetworkComponent(NetworkComponent toLoad);

    boolean unloadNetworkComponent(NetworkComponent toUnload);

    ArrayList<NetworkComponent> getNetworkComponents();

    Map<Object, NetworkComponent> getNetworkMap();

    default NetworkComponent getNetworkComponentAt(Object position) {
        return getNetworkMap().get(position);
    }

    default Map<Object, NetworkComponent> getNeighbours(NetworkComponent component) {
        return component.getNeighbours();
    }

    void updateNetwork();
}
