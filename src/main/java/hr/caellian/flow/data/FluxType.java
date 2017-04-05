package hr.caellian.flow.data;

import java.io.Externalizable;

/**
 * Flux Types are used to differentiate and construct different types of flux objects.
 *
 * @param <B> {@link FluxType Flux type} child {@link Flux flux} class.
 *
 * @author Caellian
 * @since 1.0.0
 */
public interface FluxType<B extends Flux> extends Externalizable {
    /**
     * @return ID of this Flux Type.
     */
    String getID();

    /**
     * This method is used for creating {@link Flux} objects.
     *
     * @param properties properties to include in created {@link Flux} object.
     * @return instance of {@link Flux} object of this {@link FluxType Flux Type}.
     */
    B createUnit(Property... properties);
}
