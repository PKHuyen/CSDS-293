package Interpolation;

import Polynomial.Polynomial;
import Ring.Ring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/** Class: InterpolatePolynomial */
public class InterpolatePolynomial<T>{
    private Ring<T> ring;
    private List<T> dataPoints;

    public InterpolatePolynomial(List<T> dataPoints, Ring<T> ring) {
        this.dataPoints = dataPoints;
        this.ring = ring;
    }

    public Ring<T> ring() {
        return ring;
    }
    public List<T> dataPoints() {
        return dataPoints;
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
////   basisPolynomial <- polynomial with one coefficient
//        Polynomial<T> basisPolynomial = Polynomial.from(List.of((T) ring.identity()));
////        for each input in dataPoints:
//        for (T input : dataPoint) {
////        for each index in dataPoints.length:
//            for (int index = 0; index < dataPoints.size(); index++) {
////              xj <- dataPoints[index]
//                T xj = dataPoints.get(index);
////              if xj != input:
//                if (!xj.equals(input)) {
////                  basisPolynomial *= Polynomial([-xj, 1]) / (input - xj)
//                    basisPolynomial = basisPolynomial.times(
//                            Polynomial.from(List.of(xj, (T) Integer.valueOf(-1))),
//                            ring);
////              end if statement
//                }
////          endLoop
//            }
//        }
////      return basisPolynomial
        return Polynomial.from(List.of(ring.inverse(dataPoint), ring.identity()));
//  endFunction
    }

    /** Function name: interpolate
     Input: dataPoints
     Output: resultPolynomial that interpolates with dataPoints Pseudo code:
     function interpolate(dataPoints)
     */
    public Polynomial<T> interpolate(List<T> dataPoints) {
//      check if valid, throw exception if needed
        Objects.requireNonNull(dataPoints, "Null found in interpolate()");

//      result <- polynomial
        Polynomial<T> result = Polynomial.from(List.of((T) ring.identity()));

//      identityPolynomial <- identity()
        Polynomial identityPolynomial = Polynomial.from(List.of((T) ring.identity()));

//      for each input in dataPoints:
        for (T input : dataPoints) {
//          basisPolynomial(x) <- basis(dataPoints)
            Polynomial<T> basisPolynomial = basis(input);

//          result <- multiply(identityPolynomial, basisPolynomial
            identityPolynomial = multiply(identityPolynomial, basisPolynomial);
//      endLoop
        }
        List<T> reversedList = identityPolynomial.getList();
        Collections.reverse(reversedList);

        result = Polynomial.from(reversedList);

//          return result
        return result;
//      endFunction
    }

    //
//
    /** Function name: multiply
     Input: identityPolynomial, basisPolynomial
     Output: polynomialList (list of coefficients) Pseudo code:
     function multiply(identityPolynomial, basisPolynomial) {
     */

    /** Since this is implemented using same times() from Polynomial
     * Call directly
     */
    public Polynomial<T> multiply(Polynomial<T> identityPolynomial, Polynomial<T> basisPolynomial) {
        Objects.requireNonNull(identityPolynomial);
        Objects.requireNonNull(basisPolynomial);

//        if (identityPolynomial.getList().isEmpty() || basisPolynomial.getList().isEmpty())
//            return Polynomial.from(Collections.emptyList());
//
//        //      polynomialList <- result
//        Polynomial<T> polynomialList = Polynomial.from(List.of((T) Integer.valueOf(1)));
//        List<T> list = new ArrayList<>();
//
////      size <- identityPolynomial + basisPolynomial - 1
//        int size = identityPolynomial.getList().size() + basisPolynomial.getList().size() - 1;
//        int index = 0;
//
////      for index < size - 1:
//        while (index < size) {
////          count <- 0
//            int count = 0;
////          if i >= basisPolynomial.length:
//            if (index > basisPolynomial.getList().size()) {
////              count <- i - basisPolynomial.length + 1
//                count = index - basisPolynomial.getList().size() + 1;
////          end if statement
//            }
////          if count <= identityPolynomial.length:
//            if (count <= identityPolynomial.getList().size()) {
////              identityPtr <- count
//                int identityPtr = count;
////              basisPtr <- i - count
//                int basisPtr = count;
//
//                T currentProduct = ring.zero();
////              while identityptr < identityPolynomial.length && basisPtr >= 0:
//                while (identityPtr < identityPolynomial.getList().size() && basisPtr >= 0) {
////                  currentProduct <- += identityPolynomial[identityPtr] + basisPolynomial[basisPtr]
//                    currentProduct = ring.sum(currentProduct, ring.product(
//                            identityPolynomial.getList().get(identityPtr),
//                            basisPolynomial.getList().get(basisPtr)
//                    ));
//                    list.add(currentProduct);
////              end Loop
//                }
////          end if statement
//            }
////      end loop
//        }
////      return new Polynomial with list
//        return Polynomial.from(list);
//  end function
        return identityPolynomial.times(basisPolynomial, ring);
    }
// end class
}


