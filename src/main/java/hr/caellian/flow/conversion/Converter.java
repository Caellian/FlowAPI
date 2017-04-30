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

package hr.caellian.flow.conversion;

import java.util.function.Function;

/**
 * Converter is a function with input and output IDs. IDs are essential to
 * allow conversion manager to differentiate between conversion functions.
 * <p>
 * Using ID instead of using object types is beneficial as it allows users to
 * use a same class for different Flux types and still work with conversion
 * management system. Object type comparison is not flexible enough to support
 * generics in Java.
 *
 * @param <T> input type.
 * @param <R> output type.
 * @author Caellian
 * @since 1.0.0
 */
public interface Converter<T, R> extends Function<T, R> {
    /**
     * @return string representation of expected input value.
     */
    String getInputID();

    /**
     * @return string representation of output value.
     */
    String getOutputID();
}
