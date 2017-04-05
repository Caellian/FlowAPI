package hr.caellian.flow.network.transfer;

import hr.caellian.flow.data.Flux;
import hr.caellian.flow.network.NetworkComponent;

import java.io.Externalizable;
import java.util.ArrayList;

/**
 * {@link FluxContainer Flux Containers} are object capable of containing Flux
 * objects.
 *
 * @param <B> Flux superclass supported by instances of this
 * {@link FluxContainer Flux Container}.
 *
 * @author Caellian
 * @since 1.0.0
 */
public interface FluxContainer<B extends Flux> extends NetworkComponent, Externalizable, FluxConductor {
    /**
     * @return Flux currently stored within this Flux Container.
     */
    ArrayList<B> getFlux();

    /**
     * Implementation of this function should be capable of storing
     *
     * @param flux Flux to store in this Flux Container.
     * @return {@code true} if argument Flux was stored properly.
     */
    boolean addFlux(B flux);

    /**
     * @param ID ID of Flux to remove from this Container.
     * @return {@code true} if Flux was contained in this Flux Container,
     * {@code false} otherwise.
     */
    default boolean removeFlux(String ID) {
        boolean result = false;
        for (B flux : getFlux()) {
            if (flux.getID().equals(ID)) {
                result = result || removeFlux(flux);
            }
        }
        return result;
    }

    /**
     * @param flux Flux to remove from this Container.
     * @return {@code true} if Flux was contained in this Flux Container,
     * {@code false} otherwise.
     */
    boolean removeFlux(B flux);
}
