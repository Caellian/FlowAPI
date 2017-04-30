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
import hr.caellian.flow.data.Property;
import hr.caellian.flow.network.NetworkComponent;

import java.util.ArrayList;

/**
 * Flux receivers are objects capable of receiving a form of {@link Flux flux}.
 *
 * @param <B> Flux superclass supported by instances of this flux receiver.
 * @author Caellian
 * @since 1.0.0
 */
public interface FluxReceiver<B extends Flux> extends NetworkComponent, FluxCompatible, FluxConductor {
    /**
     * @param in         {@link Flux Flux} objects passed to this receiver.
     * @param emitter    {@link FluxEmitter Flux Emitter} which gave argument
     *                   flux objects to this receiver.
     * @param conditions conditions under which flux was transmitted.
     * @return {@link Flux Flux} objects kept in emitter that weren't passed
     * properly.
     */
    ArrayList<B> receive(ArrayList<B> in, FluxEmitter<B> emitter, Property... conditions);

    /**
     * @param in {@link Flux Flux} this flux receiver is about to receive.
     * @return {@code true} if this flux receiver can receive argument {@link
     * Flux Flux}.
     */
    boolean canReceiveFlux(B in);

    /**
     * @return {@code true} if this flux receiver can receive {@link Flux Flux}.
     */
    boolean canReceiveFlux();
}
