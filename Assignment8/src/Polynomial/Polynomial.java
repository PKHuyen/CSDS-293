package Polynomial;

import Ring.Ring;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public final class Polynomial<T> implements Iterable<T>{
    // Stores the list of coefficients in the Polynomial ring
    private final List<T> coefficients;

    /** Private Constructor that sets the coefficients for Polynomial ring */
    private Polynomial(List<T> coefficients) {
        this.coefficients = coefficients;
    }

    /** from() returns new Polynomial that is immutable copy of coefficients*/
    public static final <S> Polynomial<S> from(List<S> coefficients) {
        //Error Handling
        /** Collections.unmutifiable(coefficients) => Cannot access coefficient and change directly */
        Objects.requireNonNull(coefficients, "Null found in from() - Polynomial");

        return new Polynomial<>(coefficients);
    }

    /** Return the mutable list of coefficients*/
    public List<T> getList() {
        return coefficients.stream()
                .collect(Collectors.toList());
    }

    /** Override toString() to print list of coefficients */
    @Override
    public String toString() {
        return Arrays.toString(getList().toArray());
    }

    /** First way of iterate: Override the iterator() method in Iterable interface*/
    @Override
    public Iterator<T> iterator() {
        return getList().iterator();
    }

    /** Second way of iterate: using listIterator so that the initial call to next results in coefficients.get(i)*/
    public ListIterator<T> listIterator(int i) {
        return getList().listIterator(i);
    }

    /** Addition method for Polynomial */
    public Polynomial<T> plus(Polynomial<T> other, Ring<T> ring){
        //Error handling
        Objects.requireNonNull(other, "Null found in plus() - Polynomial");
        Objects.requireNonNull(ring, "Null found in plus() - Polynomial");

        List<T> result = new ArrayList<>();
        int size = Math.max(this.coefficients.size(), other.coefficients.size());

        for (int i = 0; i < size; i++) {
            //while i < size, get value, or else return 0
            T tCoefficient = plusHelper(ring, this.coefficients, i);
            T oCoefficient = plusHelper(ring, other.coefficients, i);;
            result.add(ring.sum(tCoefficient, oCoefficient));
        }
        return new Polynomial<T>(result.stream().collect(Collectors.toList()));
    }

    /** Plus helper method to assist for loop */
    private T plusHelper(Ring<T> ring, List<T> thisCoefficient, int i) {
        assert ring != null;
        assert thisCoefficient != null;

        return i < thisCoefficient.size() ? thisCoefficient.get(i) : ring.zero();
    }

    /**Times method for polynomial */
    public Polynomial<T> times(Polynomial<T> other, Ring<T> ring) {
        //Error Handling
        Objects.requireNonNull(other, "Null found in times() - Polynomial");
        Objects.requireNonNull(ring, "Null found in times() - Polynomial");

        if (this.coefficients.isEmpty() || other.coefficients.isEmpty())
            return Polynomial.from(Collections.emptyList());

        List<T> result = timesHelper(other, ring);
        return new Polynomial<T>(result);
    }

    /** Helper method for times() - reduce complexity */
    private List<T> timesHelper(Polynomial<T> other, Ring<T> ring) {
        assert other != null;
        assert ring != null;

        List<T> list = new ArrayList<>();
        int size = this.coefficients.size() + other.coefficients.size() - 1;
        for (int i = 0; i < size; i++) {
            int thisIndex = Math.min(this.coefficients.size() - 1, i); //endIndex
            int otherIndex = i - thisIndex; //startIndex

            ListIterator<T> iteratorThis = this.listIterator(thisIndex + 1);
            ListIterator<T> iteratorOther = other.listIterator(otherIndex);

            T currentProduct = ring.zero();

            while (check(iteratorThis, iteratorOther)) {
                currentProduct = ring.sum(currentProduct, ring.product(iteratorThis.previous(), iteratorOther.next()));
            }
            list.add(currentProduct);
        }
        return list;
    }

    /** Helper method to reduce complexity of timesHelper*/
    private boolean check(ListIterator<T> iteratorThis, ListIterator<T> iteratorOther) {
        assert iteratorOther != null;
        assert iteratorThis != null;
        return iteratorThis.hasPrevious() && iteratorOther.hasNext();
    }

    /** Testign private helper method
     * Test included: plusHelper(), timesHelper(), check()
     * */
    public static class HelperMethodTest {
        public static <T> T plusHelper(Ring<T> ring, List<T> thisCoefficient, int i) {
            Polynomial<T> p = new Polynomial(thisCoefficient);
            T res = p.plusHelper(ring, thisCoefficient, 0);
            return res;
        }

        public static <T> List<T> timesHelper(Polynomial<T> other, Ring<T> ring, List<T> thisCoefficient) {
            assert thisCoefficient != null;
            Polynomial<T> p = new Polynomial(thisCoefficient);
            List<T> res = p.timesHelper(other, ring);
            return res;
        }

        public static <T> boolean check(ListIterator<T> iteratorThis, ListIterator<T> iteratorOther, List<T> thisCoefficient) {
            assert thisCoefficient != null;
            Polynomial<T> p = new Polynomial<>(thisCoefficient);
            return p.check(iteratorThis, iteratorOther);
        }
    }
}