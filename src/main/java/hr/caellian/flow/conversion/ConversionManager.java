package hr.caellian.flow.conversion;

import hr.caellian.flow.data.Pair;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Conversion Manager allows conversion between different {@code Flux} types.
 * <p>
 * Conversion process has to be registered separately for every conversion. This
 * system prevents unintentional conversion between {@code Flux} types that
 * shouldn't work together.
 *
 * @param <T> {@link Converter converter} type subclass supported by {@link ConversionManager Conversion Manager}.
 *
 * @author Caellian
 * @since 1.0.0
 */
public abstract class ConversionManager<T extends Converter> {

    /**
     * Using this method allows Conversion Managers to dictate which map data
     * format they want to use. This allows multiple Conversion Managers to use
     * the same map, to load map from disk or download it from internet and to
     * perform other forms of transfer or map manipulation.
     *
     * @return access to converter map stored in this manager.
     */
    protected abstract Map<Pair<String, String>, T> getConverterMap();

    /**
     * This method will register a power converter safely without forcing it's
     * registration and usage.
     *
     * @param converter converter to register.
     * @return {@code true} if converter wasn't previously registered and was
     * successfully registered, {@code false} otherwise.
     */
    public boolean register(T converter) {
        //noinspection deprecation
        return register(converter, false);
    }

    /**
     * This method will register a power converter safely allowing you to
     * force it's registration and usage.
     *
     * There is no guarantee that implementation of this function will or can
     * respect {@code force} parameter.
     *
     * @param powerUnitConverter converter to register.
     * @param force true to force registration.
     * @return {@code true} if converter wasn't previously registered and was
     * successfully registered, {@code false} otherwise.
     */
    @Deprecated
    public abstract boolean register(T powerUnitConverter, boolean force);

    /**
     * @param from ID of input flux type.
     * @param to ID of output flux type.
     * @return {@code true} if a direct conversion function for input and output
     * flux IDs is available, {@code false} otherwise.
     */
    public boolean converterAvailable(String from, String to) {
        return getConverter(from, to) != null;
    }

    /**
     * @param from ID of input flux type.
     * @param to ID of output flux type.
     * @param maxSteps number of steps to find indirect conversion function
     *                 within.
     * @return {@code true} if an indirect conversion function for input and
     * output flux IDs is available within defined number of steps,
     * {@code false} otherwise.
     */
    public boolean converterAvailable(String from, String to, int maxSteps) {
        return getConverter(from, to, maxSteps) != null;
    }

    /**
     * @param from ID of input flux type.
     * @param to ID of output flux type.
     * @return direct converter form input to output flux type, {@code null}
     * otherwise.
     */
    public T getConverter(String from, String to) {
        return getConverterMap().get(new Pair<>(from, to));
    }

    /**
     * @param from ID of input flux type.
     * @param to ID of output flux type.
     * @param maxSteps number of steps to find indirect conversion function
     *                 within.
     * @return indirect converter if an indirect conversion function for input
     * and output flux IDs is available within defined number of steps,
     * {@code null} otherwise.
     */
    public T getConverter(String from, String to, int maxSteps) {
        // Try to find direct converter to save time.
        T direct = getConverter(from, to);
        if (direct != null) {
            return direct;
        }

        // Get local map pointer to reduce method calls and improve performance.
        Map<Pair<String, String>, T> converterMap = getConverterMap();

        final int[] lowestScore = {maxSteps};
        //noinspection unchecked
        final T[] lowest = (T[]) new Function[1];

        Consumer<Object[]> recursiveBFS = new Consumer<Object[]>() {
            @Override
            public void accept(Object[] args) {
                Consumer<Object[]> recursiveBFS = this;

                converterMap.forEach((IDPair, converter) -> {
                    if (args[0].equals(IDPair.getA())) {
                        if (Objects.equals(IDPair.getB(), to)) {
                            if (((int) args[2]) < lowestScore[0]) {
                                lowestScore[0] = ((int) args[2]);
                                //noinspection unchecked
                                lowest[0] = (T) ((Function) args[1]).apply(converter);
                            }
                        } else if (((int) args[2]) < lowestScore[0]) {
                            if (args[1] != null) {
                                //noinspection unchecked
                                recursiveBFS.accept(new Object[]{IDPair.getB(), ((Function) args[1]).apply(converter), ((int) args[2]) + 1});
                            } else {
                                recursiveBFS.accept(new Object[]{IDPair.getB(), converter, ((int) args[2]) + 1});
                            }
                        }
                    }
                });
            }
        };

        // Perform search and construction of simplest conversion function.
        // This will not return the most accurate conversion function, just the least complex one.
        recursiveBFS.accept(new Object[]{from, null, 0});

        return lowest[0];
    }
}
