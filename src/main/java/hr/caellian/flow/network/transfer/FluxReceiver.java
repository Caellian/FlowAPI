package hr.caellian.flow.network.transfer;

import hr.caellian.flow.data.Flux;
import hr.caellian.flow.data.Property;
import hr.caellian.flow.network.NetworkComponent;

/**
 * {@link FluxReceiver Flux Receivers} are objects capable of receiving a
 * form of {@link Flux Flux}.
 *
 * @param <B>
 */
public interface FluxReceiver<B extends Flux> extends NetworkComponent, FluxCompatible, FluxConductor {
    boolean recieve(B[] in, FluxEmitter<B> emitter, Property... conditions);

    boolean canReceiveFlux();
}
