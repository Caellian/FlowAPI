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

import hr.caellian.flow.conversion.Converter;
import hr.caellian.flow.data.Flux;
import hr.caellian.flow.data.FluxType;

/**
 * Flux compatible objects are objects compatible with multiple flux types.
 * <p>
 * Flux compatible objects have a preferred flux type
 * and are capable of precisely selecting flux types they can operate with.
 * <p>
 * {@link #getConverter(FluxType)} method must return a converter from input
 * flux type to preferred flux type if {@link #supportsFluxType(FluxType)}
 * method returns {@code true} for same argument.
 *
 * @author Caellian
 * @since 1.0.0
 */
public interface FluxCompatible {
    /**
     * @return preferred flux type of this object.
     */
    FluxType<Flux> getPreferredType();

    /**
     * @param type flux type to check for compatibility.
     * @return {@code true} if object supports argument flux type, {@code false}
     * otherwise.
     */
    boolean supportsFluxType(FluxType<Flux> type);

    /**
     * @param type flux type to return converter for.
     * @param <C>  converter type.
     * @return converter from input flux type to preferred flux type if
     * available, {@code null} otherwise.
     */
    <C extends Converter> C getConverter(FluxType<Flux> type);
}
