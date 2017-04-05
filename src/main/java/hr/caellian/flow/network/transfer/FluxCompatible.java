package hr.caellian.flow.network.transfer;

import hr.caellian.flow.conversion.Converter;
import hr.caellian.flow.data.FluxType;

/**
 * {@link FluxCompatible Flux Compatible} objects are objects compatible with
 * multiple flux types.
 * <p>
 * {@link FluxCompatible Flux Compatible} objects have a preferred flux type
 * and are capable of precisely selecting flux types they can operate with.
 * <p>
 * {@link #getConverter(FluxType)} method must return a converter from input
 * flux type to preferred flux type if {@link #supportsFluxType(FluxType)}
 * method returns {@code true} for same argument.
 *
 * @author Caellian
 * @since 1.0.0
 */
public interface FluxCompatible {
    /**
     * @return preferred flux type of this object.
     */
    FluxType getPreferredType();

    /**
     * @param type flux type to check for compatibility.
     * @return {@code true} if object supports argument flux type, {@code false}
     * otherwise.
     */
    boolean supportsFluxType(FluxType type);

    /**
     * @param type flux type to return converter for.
     * @param <C> converter type.
     * @return converter from input flux type to preferred flux type if
     * available, {@code null} otherwise.
     */
    <C extends Converter> C getConverter(FluxType type);
}
