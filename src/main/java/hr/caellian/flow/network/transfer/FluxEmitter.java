package hr.caellian.flow.network.transfer;

import hr.caellian.flow.data.Flux;
import hr.caellian.flow.network.NetworkComponent;

import java.util.ArrayList;

/**
 * {@link FluxEmitter Flux Emitters} are objects capable of emitting flux to relative
 * {@link FluxReceiver Flux Receivers} in network.
 *
 * @param <B> Flux superclass supported by instances of this Flux Emitter.
 */
public interface FluxEmitter<B extends Flux> extends NetworkComponent, FluxConductor {
    /**
     * @param receiver {@link FluxReceiver Flux Receiver} receiving Flux from
     *                 this {@link FluxEmitter Flux Emitters}
     * @return Flux emitted by this {@link FluxEmitter Flux Emitter}
     */
    ArrayList<B> emit(FluxReceiver<B> receiver);

    /**
     * @return {@link true} if this object can currently emmit flux,
     * {@link false} otherwise.
     */
    boolean canEmitFlux();
}
