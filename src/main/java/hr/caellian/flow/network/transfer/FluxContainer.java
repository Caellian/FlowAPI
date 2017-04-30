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

package hr.caellian.flow.network.transfer;

import hr.caellian.flow.data.Flux;
import hr.caellian.flow.network.NetworkComponent;

import java.io.Externalizable;
import java.util.ArrayList;

/**
 * Flux containers are object capable of containing Flux objects.
 *
 * @param <B> Flux superclass supported by instances of this flux container.
 * @author Caellian
 * @since 1.0.0
 */
public interface FluxContainer<B extends Flux> extends NetworkComponent, Externalizable, FluxConductor {
    /**
     * @return Flux currently stored within this Flux Container.
     */
    ArrayList<B> getFlux();

    /**
     * Implementation of this function should be capable of storing
     *
     * @param flux Flux to store in this Flux Container.
     * @return {@code true} if argument Flux was stored properly.
     */
    boolean addFlux(B flux);

    /**
     * @param ID ID of Flux to remove from this Container.
     * @return {@code true} if Flux was contained in this Flux Container, {@code
     * false} otherwise.
     */
    default boolean removeFlux(String ID) {
        boolean result = false;
        for (B flux : getFlux()) {
            if (flux.getID().equals(ID)) {
                result = result || removeFlux(flux);
            }
        }
        return result;
    }

    /**
     * @param flux Flux to remove from this Container.
     * @return {@code true} if Flux was contained in this Flux Container, {@code
     * false} otherwise.
     */
    boolean removeFlux(B flux);
}
