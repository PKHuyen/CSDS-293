package Assignment4_5.Test;

import Assignment2.BigIntegerRing;
import Assignment2.Ring;
import Assignment3.Polynomial;
import Assignment3.PolynomialRing;
import Assignment4_5.MatrixMap;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatrixMapOperationPolynomialBigIntegerTest {
    @Test
    void testAdditionSquarePolynomial() {
        PolynomialRing<BigInteger> polynomialRing = PolynomialRing.instance(new BigIntegerRing());
        MatrixMap<Polynomial<BigInteger>> polyMap = MatrixMap.constant(2,
                Polynomial.from(Arrays.asList(new BigInteger("1"), new BigInteger("2"))));
        MatrixMap<Polynomial<BigInteger>> result = MatrixMap.constant(2,
                Polynomial.from(Arrays.asList(new BigInteger("2"), new BigInteger("4"))));

        assertEquals(result.toString(),
                polyMap.plus(polyMap, (x, y) -> polynomialRing.sum(x,y)).toString());
    }

    @Test
    void testAdditionNonSquarePolynomial() {
        PolynomialRing<BigInteger> polynomialRing = PolynomialRing.instance(new BigIntegerRing());
        MatrixMap<Polynomial<BigInteger>> polyMap1 = MatrixMap.constant(2,
                Polynomial.from(Arrays.asList(new BigInteger("1"), new BigInteger("2"))));
        MatrixMap<Polynomial<BigInteger>> polyMap2 = MatrixMap.constant(2,
                Polynomial.from(Arrays.asList(new BigInteger("1"), new BigInteger("2"), new BigInteger("3"))));
        MatrixMap<Polynomial<BigInteger>> result = MatrixMap.constant(2,
                Polynomial.from(Arrays.asList(new BigInteger("2"), new BigInteger("4"), new BigInteger("3"))));

        assertEquals(result.toString(),
                polyMap1.plus(polyMap2, (x, y) -> polynomialRing.sum(x,y)).toString());
    }

    @Test
    void testAdditionNull() {
        PolynomialRing<BigInteger> polynomialRing = PolynomialRing.instance(new BigIntegerRing());
        MatrixMap<Polynomial<BigInteger>> polyMap = MatrixMap.constant(2,
                Polynomial.from(Arrays.asList(new BigInteger("1"), new BigInteger("2"))));
        String message = "Null found in plus - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, ()
                -> polyMap.plus(null, (x, y) -> polynomialRing.sum(x,y)).toString());
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testTimesSquareMatrix() {
        PolynomialRing polynomialRing = PolynomialRing.instance(new BigIntegerRing());

        MatrixMap<Polynomial<BigInteger>> polyMap = MatrixMap.constant(2,
                Polynomial.from(Arrays.asList(new BigInteger("1"), new BigInteger("2"))));

        assertEquals("Indexes[row=0, col=0][1, 4, 4]\n", polyMap.times(polyMap, polynomialRing).toString());
    }

    @Test
    void testTimesNonSquareMatrix() {
        PolynomialRing polynomialRing = PolynomialRing.instance(new BigIntegerRing());
        MatrixMap<Polynomial<BigInteger>> polyMap1 = MatrixMap.constant(2,
                Polynomial.from(Arrays.asList(new BigInteger("1"), new BigInteger("2"))));
        MatrixMap<Polynomial<BigInteger>> polyMap2 = MatrixMap.constant(3,
                Polynomial.from(Arrays.asList(new BigInteger("1"), new BigInteger("2"), new BigInteger("3"))));
        String message = "Null found in product() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, ()
                -> polyMap1.times(polyMap2, polynomialRing).toString());

        assertEquals(message, exception.getMessage());
    }
    
    @Test
    void testTimesNull() {
        PolynomialRing polynomialRing = PolynomialRing.instance(new BigIntegerRing());
        MatrixMap<Polynomial<BigInteger>> polyMap = MatrixMap.constant(2,
                Polynomial.from(Arrays.asList(new BigInteger("1"), new BigInteger("2"))));

        String message = "Null found in times() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, ()
                -> polyMap.times(null, polynomialRing).toString());

        assertEquals(message, exception.getMessage());
    }
}
