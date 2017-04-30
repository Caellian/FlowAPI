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
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.function.Function;

/**
 * Property is data wrapper which makes {@link Flux} data manipulation a
 * bit easier and predictable.
 * Custom implementations of {@link Property} can handle custom serialization
 * and deserialization or handle certain formats differently.
 *
 * @param <T> Type contained by {@link Property} object.
 * @author Caellian
 * @author Strikingwolf
 * @since 1.0.0
 */
public class Property<T> implements Externalizable, Cloneable {
    /**
     * ID of this property.
     */
    protected String ID;
    /**
     * Data contained by this property.
     */
    protected T data;

    /**
     * Empty constructor which is available purely for serialization and
     * deserialization purposes.
     */
    public Property() {
    }

    /**
     * Default constructor.
     *
     * @param ID   ID of this property.
     * @param data data contained by this property.
     */
    public Property(String ID, T data) {
        this.ID = ID;
        this.data = data;
    }

    /**
     * @return ID of this property.
     */
    public String getID() {
        return ID;
    }

    /**
     * @return currently stored data.
     */
    public T get() {
        return data;
    }

    /**
     * @param defaultValue value to return if stored data is null.
     * @return currently stored data or argument data if stored data is null.
     */
    public T getOrDefault(T defaultValue) {
        return data != null ? data : defaultValue;
    }

    /**
     * Set method replaces currently stored data with argument data.
     *
     * @param newData data to replace current data with.
     * @return data previously stored in this {@link Property} or argument data
     * if old data was null or equal to argument data.
     */
    public T set(T newData) {
        T old = this.data;
        this.data = newData;
        return old != null && old != newData ? old : newData;
    }

    /**
     * @param function {@link Function} to apply to data of this {@link
     *                 Property}.
     * @param <O>      type of data stored in {@link Property} returned by
     *                 argument function.
     * @return {@link Property} produced by argument {@link Function} after
     * applying current data to it.
     */
    public <O> Property<O> bind(Function<T, Property<O>> function) {
        return function.apply(this.data);
    }

    /**
     * @param function {@link Function} to apply to data of this {@link
     *                 Property}.
     * @param <O>      type of data returned by argument {@link Function}.
     * @return new {@link Property} with identical ID as this {@link Property}
     * but with value returned by argument {@link Function} after applying
     * current data to it.
     */
    public <O> Property<O> map(Function<T, O> function) {
        return new Property<>(ID, function.apply(this.data));
    }

    /**
     * @param function {@link Function} to apply to data of this {@link
     *                 Property}.
     * @return this {@link Property} with it's data replaced by data produced by
     * applying current data to argument {@link Function}.
     */
    public Property<T> apply(Function<T, T> function) {
        this.data = function.apply(data);
        return this;
    }

    /**
     * @return {@link String} representation of this property.
     */
    @Override
    public String toString() {
        return "Property:{ID: '" + ID + "', data: " + data.toString() + "}";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        super.clone();
        return new Property<>(ID, data);
    }

    /**
     * @param out object to store data to.
     * @throws IOException includes any I/O exceptions that may occur.
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(ID);
        out.writeObject(data);
    }

    /**
     * @param in object to read external data from.
     * @throws IOException            includes any I/O exceptions that may
     *                                occur.
     * @throws ClassNotFoundException if the class for an object being restored
     *                                cannot be found.
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        ID = (String) in.readObject();
        //noinspection unchecked
        data = (T) in.readObject();
    }
}
