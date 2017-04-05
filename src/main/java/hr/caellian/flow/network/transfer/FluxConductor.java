package hr.caellian.flow.network.transfer;

import hr.caellian.flow.data.Property;

import java.util.ArrayList;
import java.util.Objects;

/**
 * {@link FluxConductor Flux Conductors} are objects which support Flux flow and
 * contain properties which can affect it.
 *
 * @author Caellian
 * @since 1.0.0
 */
public interface FluxConductor {
    /**
     * @return array containing properties of this conductor.
     */
    ArrayList<Property> getProperties();

    /**
     * @param ID ID of property to search for.
     * @return {@link true} if this conductor has a property with the specified
     * ID, {@link false} otherwise.
     */
    default boolean hasProperty(String ID) {
        for (Property property : getProperties()) {
            if (Objects.equals(property.getID(), ID)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param ID ID of property to search for.
     * @return Property with specified ID.
     */
    default Property getProperty(String ID) {
        for (Property property : getProperties()) {
            if (Objects.equals(property.getID(), ID)) {
                return property;
            }
        }
        return null;
    }
}
