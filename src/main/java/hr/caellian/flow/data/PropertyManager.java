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

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

/**
 * Property Manager is an interface marking a class capable of containing
 * {@link Property Properties} in a form of a map.
 *
 * @author Caellian
 * @since 1.0.0
 */
public interface PropertyManager {

    /**
     * @return modifiable map containing {@link Property Properties} owned by
     * this manager.
     */
    Map<String, Property> getModifiableProperties();

    /**
     * @return read-only map containing {@link Property Properties} owned by
     * this manager.
     * @see #getModifiableProperties() Modifiable verison of this map.
     */
    default Map<String, Property> getProperties() {
        return Collections.unmodifiableMap(getModifiableProperties());
    }

    /**
     * Stores argument property in this Property Manager.
     *
     * @param property property to store in this Property Manager.
     * @param <T>      argument property type.
     */
    default <T> void addProperty(Property<T> property) {
        if (property != null) {
            this.<T>getModifiableProperties().put(property.getID(), property);
        }
    }

    /**
     * This method will modify already registered property or add it to the map
     * if it isn't currently contained within the map.
     *
     * @param id       id of a property to set value to.
     * @param newValue value to set for given property id.
     * @param <T>      property type.
     * @return previous value of modified property or {@code null} if property
     * map doesn't contain said property.
     */
    default <T> T setValue(String id, T newValue) {
        if (getProperties().containsKey(id)) {
            T old = this.<T>getProperty(id).get();
            this.<T>getProperty(id).set(newValue);
            return old;
        }
        getModifiableProperties().put(id, new Property<>(id, newValue));
        return null;
    }

    /**
     * @param id  id of requested property.
     * @param <T> property type.
     * @return property stored by this Property Manager represented with
     * argument id.
     */
    default <T> Property<T> getProperty(String id) {
        //noinspection unchecked
        return this.getProperties().get(id);
    }

    /**
     * @param id           id of requested property.
     * @param defaultValue value of property returned if requested property
     *                     doesn't exist.
     * @param <T>          property type.
     * @return property stored by this Property Manager represented with
     * argument id. If requested property isn't stored in this Property Manager,
     * property with default value is returned.
     */
    default <T> Property<T> getPropertyWithDefault(String id, T defaultValue) {
        //noinspection unchecked
        return this.getProperties().getOrDefault(id, new Property<>(id, defaultValue));
    }

    /**
     * @param id  id of requested property value.
     * @param <T> type of property.
     * @return value of property stored by this Property Manager represented
     * with argument id.
     */
    default <T> T getValue(String id) {
        return this.<T>getProperty(id).data;
    }

    /**
     * @param id           id of requested property.
     * @param defaultValue value returned if requested property doesn't exist.
     * @param <T>          property type.
     * @return value of property stored by this Property Manager represented
     * with argument id. If requested property isn't stored in this Property
     * Manager, default value is returned.
     */
    default <T> T getValueWithDefault(String id, T defaultValue) {
        return getPropertyWithDefault(id, defaultValue).data;
    }

    /**
     * Performs the argument function on property with argument id which returns
     * a property value of same type as original.
     *
     * @param id   id of property which will be modified by argument function.
     * @param func function to perform on property with argument id.
     * @param <T>  property type.
     * @return Property modified by argument function.
     */
    default <T> Property<T> apply(String id, Function<T, T> func) {
        Property<T> prop = getProperty(id);
        prop.apply(func);

        // In case setValue was overridden, as if we did a deep copy
        setValue(id, prop.data);

        return prop;
    }

    /**
     * Performs the argument function on property with argument id.
     *
     * @param id   id of property which will be modified by argument function.
     * @param func mapping function to perform on property with argument id.
     * @param <I>  input property type.
     * @param <O>  output property type.
     * @return Property mapped to one represented with argument id by argument
     * function.
     */
    default <I, O> Property<O> map(String id, Function<I, O> func) {
        Property<I> prop = getProperty(id);
        return prop.map(func);
    }

    /**
     * @param id   id of property value of which to pass to argument binding
     *             function.
     * @param func binding function to perform on property with argument id.
     * @param <I>  input property type.
     * @param <O>  output property type.
     * @return Property produced by argument function.
     */
    default <I, O> Property<O> bind(String id, Function<I, Property<O>> func) {
        Property<I> prop = getProperty(id);
        return prop.bind(func);
    }
}
