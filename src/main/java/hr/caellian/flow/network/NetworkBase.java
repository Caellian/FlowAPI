package hr.caellian.flow.network;

import hr.caellian.flow.network.structure.StructureComponent;
import hr.caellian.flow.network.structure.StructureCore;

import java.util.*;

/**
 * @author caellian
 *         Created on 23/07/16 at 01:58 CET.
 */
public abstract class NetworkBase implements Network {
    /**
     * Position to network component map.
     */
    private HashMap<Object, NetworkComponent> positionMap = new HashMap<>();

    @Override
    public boolean addNetworkComponent(NetworkComponent toRegister) {
        if (toRegister instanceof StructureComponent && !((StructureComponent) toRegister).isDelegate()) {
            return positionMap.put(toRegister.getPosition(), toRegister) != toRegister;
        } else if (toRegister instanceof StructureComponent) {
            StructureCore structureCore;
            if (!((StructureComponent) toRegister).isDelegate()) {
                structureCore = (StructureCore) toRegister;
            } else {
                structureCore = ((StructureComponent) toRegister).getStructureCore();
            }
            return positionMap.put(structureCore.getPosition(), structureCore) != structureCore;
        } else {
            return positionMap.put(toRegister.getPosition(), toRegister) != toRegister;
        }
    }

    @Override
    public boolean removeNetworkComponent(NetworkComponent toRemove) {
        return positionMap.remove(toRemove.getPosition()) != null;
    }

    @Override
    public boolean loadNetworkComponent(NetworkComponent toLoad) {
        return addNetworkComponent(toLoad);
    }

    @Override
    public boolean unloadNetworkComponent(NetworkComponent toUnload) {
        return removeNetworkComponent(toUnload);
    }

    @Override
    public ArrayList<NetworkComponent> getNetworkComponents() {
        return new ArrayList<>(positionMap.values());
    }

    @Override
    public Map<Object, NetworkComponent> getNetworkMap() {
        return Collections.unmodifiableMap(positionMap);
    }

    protected HashMap<Object, NetworkComponent> getNetworkMapAccess() {
        return positionMap;
    }
}
