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
     * @return modifiable map containing {@link Property Properties} owned by this manager.
     */
    Map<String, Property> getModifiableProperties();

    /**
     * @see #getModifiableProperties() Modifiable verison of this map.
     * @return read-only map containing {@link Property Properties} owned by this manager.
     */
    default Map<String, Property> getProperties() {
        return Collections.unmodifiableMap(getModifiableProperties());
    }

    default <T> void registerProperty(Property<T> property) {
        this.<T>getModifiableProperties().put(property.getID(), property);
    }

    /**
     * This method will modify already registered property or add it to the map if it
     * isn't currently contained within the map.
     * @param id id of a property to set value to.
     * @param newValue value to set for given property id.
     * @param <T> type of property.
     * @return previous value of modified property or {@code null} if property map
     * doesn't contain said property.
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

    default <T> Property<T> getProperty(String id) {
        //noinspection unchecked
        return this.getProperties().get(id);
    }

    default <T> Property<T> getPropertyWithDefault(String id, T defaultValue) {
        //noinspection unchecked
        return this.getProperties().getOrDefault(id, new Property<>(id, defaultValue));
    }

    default <T> T getValue(String id) {
        return this.<T>getProperty(id).data;
    }

    default <T> T getValueWithDefault(String id, T defaultValue) {
        return getPropertyWithDefault(id, defaultValue).data;
    }

    default <T> Property<T> apply(String id, Function<T, T> func) {
        Property<T> prop = getProperty(id);
        prop.apply(func);

        // In case setValue was overridden, as if we did a deep copy
        setValue(id, prop.data);

        return prop;
    }

    default <T, T2> Property<T2> map(String id, Function<T, T2> func) {
        Property<T> prop = getProperty(id);
        return prop.map(func);
    }

    default <T, T2> Property<T2> bind(String id, Function<T, Property<T2>> func) {
        Property<T> prop = getProperty(id);
        return prop.bind(func);
    }
}
