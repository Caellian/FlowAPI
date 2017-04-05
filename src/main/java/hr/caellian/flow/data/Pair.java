package hr.caellian.flow.data;

import java.util.Objects;

/**
 * Container for value pairs.
 *
 * @param <A> First type.
 * @param <B> Second type.
 *
 * @author Caellian
 * @since 1.0.0
 */
public class Pair<A, B> {
    /**
     * First value.
     */
    private A _a;
    /**
     * Second value.
     */
    private B _b;

    /**
     * Default pair constructor.
     * @param a first value.
     * @param b second value.
     */
    public Pair(A a, B b) {
        _a = a;
        _b = b;
    }

    /**
     * @return first value.
     */
    public A getA() {
        return _a;
    }

    /**
     * @param a new value for {@link #_a first value}.
     */
    public void setA(A a) {
        _a = a;
    }

    /**
     * @return second value.
     */
    public B getB() {
        return _b;
    }

    /**
     * @param b new value for {@link #_b second value}.
     */
    public void setB(B b) {
        _b = b;
    }

    /**
     * @return {@link String} representation of pair.
     */
    @Override
    public String toString() {
        return "Pair{" + _a + ", " + _b + '}';
    }

    /**
     * @param o pair to compare this pair to.
     * @return {@code true} if this pair is equal to argument pair,
     * {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> that = (Pair<?, ?>) o;
        return Objects.equals(_a, that._a) &&
            Objects.equals(_b, that._b);
    }

    /**
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(_a, _b);
    }
}
