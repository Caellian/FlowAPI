/*
 * The MIT License (MIT)
 * Flow API, API for managing transfer of abstract data.
 * Copyright (c) 2017 Tin Švagelj <tin.svagelj.email@gmail.com> a.k.a. Caellian
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package hr.caellian.flow.network;

import hr.caellian.flow.network.structure.StructureComponent;
import hr.caellian.flow.network.structure.StructureCore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Basic implementation of Network which is suggested to be used when possible.
 *
 * @author Caellian
 * @since 1.0.0
 */
public abstract class NetworkBase implements Network {
    /**
     * Position to network component map.
     */
    private HashMap<Object, NetworkComponent> positionMap = new HashMap<>();

    /**
     * @param toAdd {@link NetworkComponent Network Component} to add to this
     *              network.
     * @return {@code true} if argument {@link NetworkComponent Network
     * Component} has been added to this network successfully.
     */
    @Override
    public boolean addNetworkComponent(NetworkComponent toAdd) {
        if (toAdd instanceof StructureComponent && !((StructureComponent) toAdd).isDelegate()) {
            return positionMap.put(toAdd.getPosition(), toAdd) != toAdd;
        } else if (toAdd instanceof StructureComponent) {
            StructureCore structureCore;
            if (!((StructureComponent) toAdd).isDelegate()) {
                structureCore = (StructureCore) toAdd;
            } else {
                structureCore = ((StructureComponent) toAdd).getStructureCore();
            }
            return positionMap.put(structureCore.getPosition(), structureCore) != structureCore;
        } else {
            return positionMap.put(toAdd.getPosition(), toAdd) != toAdd;
        }
    }

    /**
     * @param toRemove {@link NetworkComponent Network Component} to remove from
     *                 this network.
     * @return {@code true} if argument {@link NetworkComponent Network
     * Component} has been removed from this network successfully.
     */
    @Override
    public boolean removeNetworkComponent(NetworkComponent toRemove) {
        return positionMap.remove(toRemove.getPosition()) != null;
    }

    /**
     * @param toLoad {@link NetworkComponent Network Component} to load.
     * @return {@code true} if argument {@link NetworkComponent Network
     * Component} has been loaded successfully.
     */
    @Override
    public boolean loadNetworkComponent(NetworkComponent toLoad) {
        return addNetworkComponent(toLoad);
    }

    /**
     * @param toUnload {@link NetworkComponent Network Component} to unload.
     * @return {@code true} if argument {@link NetworkComponent Network
     * Component} has been unloaded successfully.
     */
    @Override
    public boolean unloadNetworkComponent(NetworkComponent toUnload) {
        return removeNetworkComponent(toUnload);
    }

    /**
     * @return {@link ArrayList} containing all components in this network.
     */
    @Override
    public ArrayList<NetworkComponent> getNetworkComponents() {
        return new ArrayList<>(positionMap.values());
    }

    /**
     * @return {@link Map} of {@link NetworkComponent Network Component}
     * positions and their respective objects.
     */
    @Override
    public Map<Object, NetworkComponent> getNetworkMap() {
        return Collections.unmodifiableMap(positionMap);
    }

    /**
     * @return modifiable {@link HashMap} containing {@link NetworkComponent
     * Network Component} positions and their respective objects.
     */
    protected HashMap<Object, NetworkComponent> getNetworkMapAccess() {
        return positionMap;
    }
}
