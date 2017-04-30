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

package hr.caellian.flow.data;

import java.io.Externalizable;

/**
 * Flux is a representation of transferable unit.
 * It extends {@link PropertyManager} allowing users to store unit related
 * {@link Property Properties} and manage them easily.
 *
 * @param <S> class of {@link FluxType Flux Type}.
 * @author Caellian
 * @since 1.0.0
 */
public interface Flux<S extends FluxType> extends Externalizable, PropertyManager {
    /**
     * @return ID of this unit.
     */
    default String getID() {
        return getType().getID();
    }

    /**
     * @return parent {@link FluxType Flux Type}.
     */
    S getType();

    /**
     * Adds argument flux to this object.
     *
     * @param other flux object to add to this one.
     * @return this object or {@code null} if given flux object couldn't be
     * added to this one.
     */
    Flux<S> add(Flux<FluxType> other);

    /**
     * Creates a new flux object of same type as this object with properties
     * dependant on argument properties.
     *
     * @param subtract properties which should be subtracted from this object.
     * @param clone    properties which should be cloned from this object.
     * @return constructed flux object.
     */
    Flux<S> take(Property[] subtract, String[] clone);

    /**
     * Default equals implementation.
     *
     * @param o unit to compare to this one.
     * @return {@code true} if this unit is equal to argument one, {@code false}
     * otherwise.
     */
    default boolean equals(Flux<FluxType> o) {
        boolean ret = this.sameType(o);
        if (ret) {
            for (String key : getProperties().keySet()) {
                Property ours = this.getProperty(key);
                Property theirs = this.getProperty(key);
                ret = ret && ours.equals(theirs);
            }
        }
        return ret;
    }

    /**
     * @param o unit type of which to compare to type of this unit.
     * @return {@code true} if type of this unit is equal to type argument one,
     * {@code false} otherwise.
     */
    default boolean sameType(Flux<FluxType> o) {
        return o != null && this.getType() == o.getType();
    }
}
