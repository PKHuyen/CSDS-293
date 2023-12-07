/**
 * @author Harley Phung
 * Email hkp15
 */
package Assignment3;

import Assignment2.Ring;
import java.util.*;
import java.util.stream.Collectors;

/** Polynomial of the Ring that Implementing Iterable
 * @param <T> Type of Polynomial
 * */
public final class Polynomial<T> implements Iterable<T>{
    /** Stores the list of coefficients in the Polynomial ring */
    private final List<T> coefficients;

    /**
     * Private Constructor that sets the coefficients for Polynomial ring
     * @param coefficients List of coefficients of the polynomial
     * */
    private Polynomial(List<T> coefficients) {
        this.coefficients = coefficients;
    }

    /**
     * from() returns new Polynomial that is immutable copy of coefficients
     * @param coefficients List of Coefficients of the Polynomial
     * @return new Polynomial
     * @throws NullPointerException if parameter is null
     * */
    public static final <S> Polynomial<S> from(List<S> coefficients) {
        //Error Handling
        /** Collections.unmutifiable(coefficients) => Cannot access coefficient and change directly */
        Objects.requireNonNull(coefficients, "Null found in from() - Polynomial");

        return new Polynomial<>(coefficients);
    }

    /**
     * Return the mutable list of coefficients
     * @return List of the coefficients of the Polynomial
     * */
    public List<T> getList() {
        return coefficients.stream()
                .collect(Collectors.toList());
    }

    /**
     * Override toString() to print list of coefficients
     * @return string of coeffiecients of the Polynomial
     * */
    @Override
    public String toString() {
        return Arrays.toString(getList().toArray());
    }

    /**
     * First way of iterate: Override the iterator() method in Iterable interface
     * @return iterator of the list of coefficients
     * */
    @Override
    public Iterator<T> iterator() {
        return getList().iterator();
    }

    /**
     * Second way of iterate: using listIterator so that the initial call to next results in coefficients.get(i)
     * @param i index in the list of coefficients
     * @return listIterator of Polynomial
     * */
    public ListIterator<T> listIterator(int i) {
        return getList().listIterator(i);
    }

    /**
     * Addition method for Polynomial
     * @param other The other polynomial to do the calculation with
     * @param ring A type of ring that matches with this polynomial
     * @return new polynomial that is the result of summation of this and other polynomial
     * @throws NullPointerException if parameters are null
     * */
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

    /**
     * Plus helper method to assist for loop
     * @param ring  A type of ring that matches with this polynomial
     * @param thisCoefficient List of this coefficient
     * @param i index of this coefficient
     * @return value of this coeffiencient if index is in bound or additive identity if index is out of bound
     * */
    private T plusHelper(Ring<T> ring, List<T> thisCoefficient, int i) {
        return i < thisCoefficient.size() ? thisCoefficient.get(i) : ring.zero();
    }

    /**
     * Times method for polynomial
     * @param other The other polynomial to do the calculation with
     * @param ring A type of ring that matches with this polynomial
     * @return new polynomial that is the result of product of this and other polynomial
     * @throws NullPointerException if parameters are null
     * */
    public Polynomial<T> times(Polynomial<T> other, Ring<T> ring) {
        //Error Handling
        Objects.requireNonNull(other, "Null found in times() - Polynomial");
        Objects.requireNonNull(ring, "Null found in times() - Polynomial");

        if (this.coefficients.isEmpty() || other.coefficients.isEmpty())
            return new Polynomial<>(Collections.emptyList());

        List<T> result = timesHelper(other, ring);
        return new Polynomial<T>(result);
    }

    /**
     * Helper method for times() - reduce complexity
     * @param other The other polynomial to do the calculation with
     * @param ring A type of ring that matches with this polynomial
     * @throws NullPointerException if parameters are null
     * */
    private List<T> timesHelper(Polynomial<T> other, Ring<T> ring) {
        assert other != null;
        assert ring != null;

        List<T> list = new ArrayList<>();
        int size = this.coefficients.size() + other.coefficients.size() - 1;
        for (int i = 0; i < size; i++) {
            int thisIndex = Math.min(this.coefficients.size() - 1, i); //endIndex
            int otherIndex = i - thisIndex; //startIndex

            if (thisIndex < i - other.coefficients.size()) {
                list.add(ring.zero());
                continue;
            }

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

    /**
     * Helper method to reduce complexity of timesHelper
     * @param iteratorThis Iterator of this polynomial
     * @param iteratorOther Iterator of other polynomial
     * return boolean value after checking if there's value previously or after current index
     * */
    private boolean check(ListIterator<T> iteratorThis, ListIterator<T> iteratorOther) {
        assert iteratorOther != null;
        assert iteratorThis != null;
        return iteratorThis.hasPrevious() && iteratorOther.hasNext();
    }
}
