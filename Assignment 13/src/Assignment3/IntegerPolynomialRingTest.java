package Assignment3;

import Assignment2.IntegerRing;
import Assignment2.Ring;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/** Test the PolynomialRing<Polynomial<Integer>> */
public class IntegerPolynomialRingTest {
    private Ring<Polynomial<Integer>> integerPolynomial = PolynomialRing.instance(new IntegerRing());
    private PolynomialRing<Polynomial<Integer>> integerPolynomialRing = PolynomialRing.instance(integerPolynomial);

    /** Test zero() */
    @Test
    void zeroTest() {
        assertEquals(Collections.emptyList(), integerPolynomialRing.zero().getList());
    }

/** Can use toString()
 * */


    /** Test identity() */
    @Test
    void identityTest() {
        assertEquals(Arrays.toString(List.of(integerPolynomial.identity()).toArray()), integerPolynomialRing.identity().toString());
    }

    /** Test sum() */
    @Test
    void sumTestWithSameSize() {
        Polynomial<Polynomial<Integer>> p1 = Polynomial.from(List.of(Polynomial.from(List.of(0, 1, 3))));
        Polynomial<Polynomial<Integer>> p2 = Polynomial.from(List.of(Polynomial.from(List.of(100, 200, 300))));
        Polynomial<Polynomial<Integer>> res = Polynomial.from(List.of(Polynomial.from(List.of(100,201,303))));
        assertEquals(Arrays.toString(res.getList().toArray()), integerPolynomialRing.sum(p1, p2).toString());
    }

    @Test
    void sumTestWithThisLargerSize() {
        Polynomial<Polynomial<Integer>> p1 = Polynomial.from(List.of(Polynomial.from(List.of(0, 1, 3))));
        Polynomial<Polynomial<Integer>> p2 = Polynomial.from(List.of(Polynomial.from(List.of(100, 200))));
        Polynomial<Polynomial<Integer>> res = Polynomial.from(List.of(Polynomial.from(List.of(100,201,3))));
        assertEquals(Arrays.toString(res.getList().toArray()), integerPolynomialRing.sum(p1, p2).toString());
    }

    @Test
    void sumTestWithThisSmallerSize() {
        Polynomial<Polynomial<Integer>> p1 = Polynomial.from(List.of(Polynomial.from(List.of(0, 1))));
        Polynomial<Polynomial<Integer>> p2 = Polynomial.from(List.of(Polynomial.from(List.of(100, 200, 300))));
        Polynomial<Polynomial<Integer>> res = Polynomial.from(List.of(Polynomial.from(List.of(100,201,300))));
        assertEquals(Arrays.toString(res.getList().toArray()), integerPolynomialRing.sum(p1, p2).toString());
    }

    @Test
    void sumTestWithThisNotNull() {
        String errorNotification = "Null found in sum() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> integerPolynomialRing.sum(
                Polynomial.from(List.of(Polynomial.from(List.of(0,1,3)))), null));
        assertEquals(errorNotification, exception.getMessage());
    }

    @Test
    void sumTestWithThisNull() {
        String errorNotification = "Null found in sum() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> integerPolynomialRing.sum(
                null, Polynomial.from(List.of(Polynomial.from(List.of(0,1,3))))));
        assertEquals(errorNotification, exception.getMessage());
    }

    @Test
    void sumTestWithNull() {
        String errorNotification = "Null found in sum() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> integerPolynomialRing.sum(
                null, null));
        assertEquals(errorNotification, exception.getMessage());
    }

    @Test
    void productTestWithSameSize() {
        Polynomial<Polynomial<Integer>> p1 = Polynomial.from(List.of(Polynomial.from(List.of(1, 2, 3))));
        Polynomial<Polynomial<Integer>> p2 = Polynomial.from(List.of(Polynomial.from(List.of(4, 5, 6))));
        Polynomial<Polynomial<Integer>> res = Polynomial.from(List.of(Polynomial.from(List.of(4,13,28,27,18))));
        assertEquals(Arrays.toString(res.getList().toArray()), integerPolynomialRing.product(p1, p2).toString());
    }

    @Test
    void productTestWithLargerSize() {
        Polynomial<Polynomial<Integer>> p1 = Polynomial.from(List.of(Polynomial.from(List.of(1, 2, 3))));
        Polynomial<Polynomial<Integer>> p2 = Polynomial.from(List.of(Polynomial.from(List.of(4, 5))));
        Polynomial<Polynomial<Integer>> res = Polynomial.from(List.of(Polynomial.from(List.of(4,13,22,15))));
        assertEquals(Arrays.toString(res.getList().toArray()), integerPolynomialRing.product(p1, p2).toString());
    }

    @Test
    void productTestWithSmallerSize() {
        Polynomial<Polynomial<Integer>> p1 = Polynomial.from(List.of(Polynomial.from(List.of(1, 2))));
        Polynomial<Polynomial<Integer>> p2 = Polynomial.from(List.of(Polynomial.from(List.of(4, 5, 6))));
        Polynomial<Polynomial<Integer>> res = Polynomial.from(List.of(Polynomial.from(List.of(3,10,13,10))));
        assertEquals(Arrays.toString(res.getList().toArray()), integerPolynomialRing.product(p1, p2).toString());
    }

    @Test
    void productTestWithThisNotNull() {
        String errorNotification = "Null found in product() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> integerPolynomialRing.product(
                Polynomial.from(List.of(Polynomial.from(List.of(0,1,3)))), null));
        assertEquals(errorNotification, exception.getMessage());
    }

    @Test
    void productTestWithThisNull() {
        String errorNotification = "Null found in product() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> integerPolynomialRing.product(null,
                Polynomial.from(List.of(Polynomial.from(List.of(0,1,3))))));
        assertEquals(errorNotification, exception.getMessage());
    }

    @Test
    void productTestWithNull() {
        String errorNotification = "Null found in product() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> integerPolynomialRing.product(
                null, null));
        assertEquals(errorNotification, exception.getMessage());
    }
}