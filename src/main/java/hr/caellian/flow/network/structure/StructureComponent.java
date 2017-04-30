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

package hr.caellian.flow.network.structure;

import hr.caellian.flow.network.NetworkComponent;

import java.util.ArrayList;

/**
 * Structure component is a {@link NetworkComponent Network Component} consisted
 * out of multiple components.
 *
 * @author Caellian
 * @since 1.0.0
 */
public interface StructureComponent extends NetworkComponent {
    /**
     * @return {@code true} if this structure component is a structure delegate,
     * {@code false} otherwise.
     */
    boolean isDelegate();

    /**
     * @return {@code true} if this structure contains core and all required
     * delegates.
     */
    boolean isStructureValid();

    /**
     * @return {@link ArrayList} of structure components.
     */
    ArrayList<NetworkComponent> getStructureComponents();

    /**
     * @return structure core.
     */
    default StructureCore getStructureCore() {
        for (NetworkComponent component : getStructureComponents()) {
            if (component instanceof StructureCore) {
                return (StructureCore) component;
            }
        }
        return null;
    }
}
