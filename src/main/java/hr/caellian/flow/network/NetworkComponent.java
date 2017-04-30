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

import java.util.Map;

/**
 * Network component is any object which can be a part of a {@link Network
 * Network}.
 *
 * @author Caellian
 * @since 1.0.0
 */
public interface NetworkComponent {
    /**
     * @return {@link Network Network} owning this network component.
     */
    Network getNetwork();

    /**
     * @param networkClass network to check for compatibility with this
     *                     component.
     * @return {@code true} if this network component is compatible with
     * argument network.
     */
    boolean isCompatibleWithNetwork(Network networkClass);

    /**
     * @param network {@link Network Network} to give ownership over this
     *                component to.
     * @return previous owner {@link Network Network}.
     */
    Network setNetwork(Network network);

    /**
     * Creates a default {@link Network Network} for this component.
     *
     * @return default {@link Network Network} for this component.
     */
    Network createDefaultNetwork();

    /**
     * Called when component network isn't found.
     */
    void networkNotFound();

    /**
     * @return {@link Object} representing position of this network component.
     */
    Object getPosition();

    /**
     * @return {@link Map} containing neighbour {@link NetworkComponent Network
     * Component} positions and their respective objects.
     */
    Map<Object, NetworkComponent> getNeighbours();
}
