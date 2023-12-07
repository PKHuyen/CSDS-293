/**
 * @author Harley Phung
 * Email hkp15
 */package Assignment3;

import Assignment2.Ring;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Demonstration of how PolynomialRing type Polynomial works
 * @param <T> type of the PolynomialRing
 * */
public final class PolynomialRing<T> implements Ring<Polynomial<T>> {
    /** Stores the ring that was initiate in Constructor */
    private final Ring<T> ring;

    /**
     * Constructor that initialize ring
     * @param ring Type of ring that matches with polynomial ring type
     * */
    private PolynomialRing (Ring<T> ring) {
       this.ring = ring;
    }

    /**
     * instance() return the new PolynomialRing that initialize ring
     * @param ring Type of ring that matches with polynomial ring type
     * @param <T> Type of current polynomial ring
     * @return new PolynomialRing with the ring that matches this polynomial ring type
     * */
    public static <T> PolynomialRing<T> instance (Ring<T> ring) {
        return new PolynomialRing<>(ring);
    }

    /**
     * Override zero() from Ring(), return emptyList
     * @return additive identity
     * */
    @Override
    public Polynomial<T> zero() {
        return Polynomial.from(Collections.emptyList());
    }

    /**
     * Override identity() from Ring(), return identity identified in ring
     * @return multiplicative identity
     * */
    @Override
    public Polynomial<T> identity() {
        return Polynomial.from(List.of(ring.identity()));
    }

    /**
     * Override sum() from ring that used plus() from Polynomial
     * @param x First polynomial
     * @param y Second polynomial
     * @return Summation of x and y
     * @throws NullPointerException if parameters are null
     * */
    @Override
    public Polynomial<T> sum(Polynomial<T> x, Polynomial<T> y) {
        Objects.requireNonNull(x, "Null found in sum() - PolynomialRing");
        Objects.requireNonNull(y, "Null found in sum() - PolynomialRing");
        return x.plus(y, ring);
    }

    /**
     * Override product() from ring that used times() from Polynomial
     * @param x First polynomial
     * @param y Second polynomial
     * @return Product of x and y
     * @throws NullPointerException if parameters are null
     * */
    @Override
    public Polynomial<T> product(Polynomial<T> x, Polynomial<T> y) {
        Objects.requireNonNull(x, "Null found in product() - PolynomialRing");
        Objects.requireNonNull(y, "Null found in product() - PolynomialRing");
        return x.times(y, ring);
    }
}
