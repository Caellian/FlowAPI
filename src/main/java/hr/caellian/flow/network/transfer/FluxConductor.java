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

import hr.caellian.flow.data.Property;

import java.util.HashMap;

/**
 * Flux conductors are objects which support Flux flow and
 * contain properties which can affect it.
 *
 * @author Caellian
 * @since 1.0.0
 */
public interface FluxConductor {
    /**
     * @return {@link HashMap} containing properties of this conductor.
     */
    HashMap<String, Property> getProperties();

    /**
     * @param ID ID of property to search for.
     * @return {@code true} if this conductor has a property with the specified
     * ID, {@code false} otherwise.
     */
    default boolean hasProperty(String ID) {
        return getProperties().containsKey(ID);
    }

    /**
     * @param ID ID of property to search for.
     * @return Property with specified ID.
     */
    default Property getProperty(String ID) {
        return getProperties().get(ID);
    }
}
