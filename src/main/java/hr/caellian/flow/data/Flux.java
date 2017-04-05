package hr.caellian.flow.data;

import java.io.Externalizable;

/**
 * Flux is a representation of transferable unit.
 * It extends {@link PropertyManager} allowing users to store unit related
 * {@link Property Properties} and manage them easily.
 *
 * @param <S> class of {@link FluxType flux type}.
 *
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
     * Default equals implementation.
     *
     * @param o unit to compare to this one.
     * @return {@code true} if this unit is equal to argument one, {@code false}
     * otherwise.
     */
    default boolean equals(Flux o) {
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
    default boolean sameType(Flux o) {
        return o != null && this.getType() == o.getType();
    }
}
