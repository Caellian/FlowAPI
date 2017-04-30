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

import java.util.ArrayList;
import java.util.Map;

/**
 * Network is consisted out of {@link NetworkComponent Network Components}
 * capable of communicating with each other.
 * <p>
 * Networks allow updating only specific components instead of all of them which
 * is a big optimization for certain systems.
 * They can also request certain components to be loaded or unloaded if their
 * container requires it.
 *
 * @author Caellian
 * @since 1.0.0
 */
public interface Network {
    /**
     * @return id representing network class.
     */
    String getNetworkTypeID();

    /**
     * @param toAdd {@link NetworkComponent Network Component} for which this
     *              method should check if it can be added to it.
     * @return {@code true} if argument {@link NetworkComponent Network
     * Component} can be added to this network.
     */
    boolean canAddComponent(NetworkComponent toAdd);

    /**
     * @param toAdd {@link NetworkComponent Network Component} to add to this
     *              network.
     * @return {@code true} if argument {@link NetworkComponent Network
     * Component} has been added to this network successfully.
     */
    boolean addNetworkComponent(NetworkComponent toAdd);

    /**
     * @param toRemove {@link NetworkComponent Network Component} to remove from
     *                 this network.
     * @return {@code true} if argument {@link NetworkComponent Network
     * Component} has been removed from this network successfully.
     */
    boolean removeNetworkComponent(NetworkComponent toRemove);

    /**
     * @param toLoad {@link NetworkComponent Network Component} to load.
     * @return {@code true} if argument {@link NetworkComponent Network
     * Component} has been loaded successfully.
     */
    boolean loadNetworkComponent(NetworkComponent toLoad);

    /**
     * @param toUnload {@link NetworkComponent Network Component} to unload.
     * @return {@code true} if argument {@link NetworkComponent Network
     * Component} has been unloaded successfully.
     */
    boolean unloadNetworkComponent(NetworkComponent toUnload);

    /**
     * @return {@link ArrayList} containing all components in this network.
     */
    ArrayList<NetworkComponent> getNetworkComponents();

    /**
     * @return {@link Map} of {@link NetworkComponent Network Component}
     * positions and their respective objects.
     */
    Map<Object, NetworkComponent> getNetworkMap();

    /**
     * @param position position to look for {@link NetworkComponent Network
     *                 Component} at.
     * @return {@link NetworkComponent Network Component} at argument position
     * or {@code null} if it doesn't exist.
     */
    default NetworkComponent getNetworkComponentAt(Object position) {
        return getNetworkMap().get(position);
    }

    /**
     * @param component {@link NetworkComponent Network Component} whose
     *                  neighbours to return.
     * @return {@link Map} of neighbour {@link NetworkComponent Network
     * Component} positions and their respective objects.
     */
    default Map<Object, NetworkComponent> getNeighbours(NetworkComponent component) {
        return component.getNeighbours();
    }

    /**
     * Updates network components appropriately.
     */
    void updateNetwork();
}
