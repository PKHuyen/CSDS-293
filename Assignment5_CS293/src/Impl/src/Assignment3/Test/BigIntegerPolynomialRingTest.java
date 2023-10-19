package Assignment3.Test;

import Assignment2.*;
import Assignment3.Polynomial;
import Assignment3.PolynomialRing;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
/** Split the test for product, plus (equalsize, smaller, larger)
 * */

/** Test for PolynomialRing<BigInteger> */
public class BigIntegerPolynomialRingTest {
    Ring<BigInteger> bigIntegerRing = new BigIntegerRing<>();
    PolynomialRing<BigInteger> bigIntegerPolynomialRing = PolynomialRing.instance(bigIntegerRing);
    Polynomial<BigInteger> bigIntegerPolynomial = Polynomial.from(Collections.emptyList());

    /**
     * Test Zero with Polynomial
     */
    @Test
    void zeroTest() {
        assertEquals(Collections.emptyList(), bigIntegerPolynomialRing.zero().getList());
    }

    /** Test identity with Polynomial */
    @Test
    void identityTest() {
        assertEquals(Collections.singletonList(bigIntegerRing.identity()), bigIntegerPolynomialRing.identity().getList());
    }

    @Test
    void sumTestWithSameSize() {
        Polynomial<BigInteger> ring1 = bigIntegerPolynomial.from(List.of(new BigInteger("1"), new BigInteger("2"), new BigInteger("3")));
        Polynomial<BigInteger> ring2 = bigIntegerPolynomial.from(List.of(new BigInteger("100"), new BigInteger("200"), new BigInteger("300")));
        assertEquals(List.of(new BigInteger("101"), new BigInteger("202"), new BigInteger("303")), bigIntegerPolynomialRing.sum(ring1, ring2).getList());

    }
    /** Test sum() */
    @Test
    void sumTestWithThisLargerSize() {
        Polynomial<BigInteger> ring1 = bigIntegerPolynomial.from(List.of(new BigInteger("1"), new BigInteger("2"), new BigInteger("3")));
        Polynomial<BigInteger> ring2 = bigIntegerPolynomial.from(List.of(new BigInteger("100"), new BigInteger("200")));
        assertEquals(List.of(new BigInteger("101"), new BigInteger("202"), new BigInteger("3")), bigIntegerPolynomialRing.sum(ring1, ring2).getList());
    }

    @Test
    void sumTestWithThisSmallerSize() {
        Polynomial<BigInteger> ring1 = bigIntegerPolynomial.from(List.of(new BigInteger("100"), new BigInteger("200")));
        Polynomial<BigInteger> ring2 = bigIntegerPolynomial.from(List.of(new BigInteger("1"), new BigInteger("2"), new BigInteger("3")));
        assertEquals(List.of(new BigInteger("101"), new BigInteger("202"), new BigInteger("3")), bigIntegerPolynomialRing.sum(ring1, ring2).getList());
    }

    @Test
    void sumTestWithThisNotNull() {
        //Test error
        String errorNotification = "Null found in sum() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> bigIntegerPolynomialRing.sum(bigIntegerPolynomial.from(
                List.of(new BigInteger("1"), new BigInteger("2"), new BigInteger("3"))), null));
        assertEquals(errorNotification, exception.getMessage());
    }

    @Test
    void sumTestWithThisNull() {
        String errorNotification = "Null found in sum() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> bigIntegerPolynomialRing.sum(null, bigIntegerPolynomial.from(
                List.of(new BigInteger("1"), new BigInteger("2"), new BigInteger("3")))));
        assertEquals(errorNotification, exception.getMessage());
    }

    @Test
    void sumTestWithNull() {
        String errorNotification = "Null found in sum() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> bigIntegerPolynomialRing.sum(null, null));
        assertEquals(errorNotification, exception.getMessage());
    }

    /** Test product() */
    @Test
    void productTestWithSmallerSize() {
        Polynomial<BigInteger> ring1 = bigIntegerPolynomial.from(List.of(new BigInteger("1"), new BigInteger("2")));
        Polynomial<BigInteger> ring2 = bigIntegerPolynomial.from(List.of(new BigInteger("3"), new BigInteger("4"), new BigInteger("5")));
        assertEquals(List.of(
                        new BigInteger("3"), new BigInteger("10"), new BigInteger("13"), new BigInteger("10")),
                bigIntegerPolynomialRing.product(ring1, ring2).getList());

    }
    @Test
    void productTestWithThisLargerSize() {
        Polynomial<BigInteger> ring1 = bigIntegerPolynomial.from(List.of(new BigInteger("1"), new BigInteger("2"), new BigInteger("3")));
        Polynomial<BigInteger> ring2 = bigIntegerPolynomial.from(List.of(new BigInteger("4"), new BigInteger("5")));
        assertEquals(List.of(
                        new BigInteger("4"), new BigInteger("13"), new BigInteger("22"), new BigInteger("15")),
                bigIntegerPolynomialRing.product(ring1, ring2).getList()
        );
    }

    @Test
    void productTestWithThisSameSize() {
        Polynomial<BigInteger> ring1 = bigIntegerPolynomial.from(List.of(new BigInteger("1"), new BigInteger("2"), new BigInteger("3")));
        Polynomial<BigInteger> ring2 = bigIntegerPolynomial.from(List.of(new BigInteger("4"), new BigInteger("5"), new BigInteger("6")));
        assertEquals(List.of(
                        new BigInteger("4"), new BigInteger("13"), new BigInteger("28"), new BigInteger("27"), new BigInteger("18")),
                bigIntegerPolynomialRing.product(ring1, ring2).getList()
        );
    }

    @Test
    void productTestWithThisNotNull() {
        //Test product with error
        String errorNotification = "Null found in product() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> bigIntegerPolynomialRing.product(bigIntegerPolynomial.from(
                List.of(new BigInteger("1"), new BigInteger("2"), new BigInteger("3"))), null));
        assertEquals(errorNotification, exception.getMessage());
    }

    @Test
    void productTestWithThisNull() {
        String errorNotification = "Null found in product() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> bigIntegerPolynomialRing.product(null, bigIntegerPolynomial.from(
                List.of(new BigInteger("1"), new BigInteger("2"), new BigInteger("3")))));
        assertEquals(errorNotification, exception.getMessage());
    }

    @Test
    void productTestWithNull() {
        String errorNotification = "Null found in product() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> bigIntegerPolynomialRing.product(null, null));
        assertEquals(errorNotification, exception.getMessage());
    }
}