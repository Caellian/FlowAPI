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

import java.util.ArrayList;

/**
 * Flux emitters are objects capable of emitting flux to relative
 * {@link FluxReceiver flux receivers} in network.
 *
 * @param <B> Flux superclass supported by instances of this Flux Emitter.
 * @author Caellian
 * @since 1.0.0
 */
public interface FluxEmitter<B extends Flux> extends NetworkComponent, FluxConductor {
    /**
     * @param receiver {@link FluxReceiver Flux Receiver} receiving Flux from
     *                 this Flux Emitter.
     * @return Flux remaining in this Flux Emitter.
     */
    ArrayList<B> emit(FluxReceiver<B> receiver);

    /**
     * @return {@code true} if this object can currently emmit flux, {@code
     * false} otherwise.
     */
    boolean canEmitFlux();
}
