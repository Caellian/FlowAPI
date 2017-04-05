package hr.caellian.flow.conversion;

import java.util.function.Function;

/**
 * Converter is a function with input and output IDs. IDs are essential to
 * allow conversion manager to differentiate between conversion functions.
 *
 * Using ID instead of using object types is beneficial as it allows users to
 * use a same class for different Flux types and still work with conversion
 * management system. Object type comparison is not flexible enough to support
 * generics in Java.
 *
 * @param <T> input type.
 * @param <R> output type.
 *
 * @author Caellian
 * @since 1.0.0
 */
public interface Converter<T, R> extends Function<T, R> {
    /**
     * @return string representation of expected input value.
     */
    String getInputID();

    /**
     * @return string representation of output value.
     */
    String getOutputID();
}
