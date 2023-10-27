import Polynomial.Polynomial;
import Ring.Ring;

import java.util.*;
import java.util.stream.Collectors;

/** Class: InterpolatePolynomial */
public class InterpolatePolynomial<T> {
    private Ring<T> ring;
    private final List<T> dataPoints;

    public InterpolatePolynomial(List<T> coefficients, Ring<T> ring) {
        Objects.requireNonNull(coefficients, "Null found");
        Objects.requireNonNull(ring, "Null found");
        this.dataPoints = coefficients;
        this.ring = ring;
    }

    public Ring<T> ring() {
        return this.ring;
    }

    public List<T> dataPoints() {
        return this.dataPoints;
    }

/** Function name: basis
 Input: dataPoint
 Output: basisPolynomial Pseudo code:
 */

    /**
     Figured out that I'm doing multiplication inside basis, so,
     decide to only return the Polynomial of
     ring identity and inverse dataPoint.
     */
    public Polynomial<T> basis(T dataPoint) {
        Objects.requireNonNull(dataPoint, "Null found in basis()");
        return Polynomial.from(List.of(ring.inverse(dataPoint), ring.identity())); //1
    }

    /** Function name: interpolate
     Input: dataPoints
     Output: resultPolynomial that interpolates with dataPoints Pseudo code:
     function interpolate(dataPoints)
     */
    public Polynomial<T> interpolate(List<T> dataPoints) {
        Objects.requireNonNull(dataPoints, "Null found in interpolate()");
        Polynomial<T> result = Polynomial.from(List.of((T) ring.identity()));
        Polynomial identityPolynomial = Polynomial.from(List.of((T) ring.identity()));

        for (T input : dataPoints) { //1
            Polynomial<T> basisPolynomial = basis(input); //2
            identityPolynomial = multiply(identityPolynomial, basisPolynomial); //3
        }
        List<T> reversedList = identityPolynomial.getList();
        Collections.reverse(reversedList);

        result = Polynomial.from(reversedList);
        return result;
    }

    /** Function name: multiply
     Input: identityPolynomial, basisPolynomial
     Output: polynomialList (list of coefficients) Pseudo code:
     function multiply(identityPolynomial, basisPolynomial) {
     */
    public  Polynomial<T> multiply(Polynomial<T> identityPolynomial, Polynomial<T> basisPolynomial) {
        Objects.requireNonNull(identityPolynomial, "Null found in multiply - InterpolatePolynomial");
        Objects.requireNonNull(basisPolynomial, "Null found in multiply - InterpolatePolynomial");
        return identityPolynomial.times(basisPolynomial, ring);
    }
}

