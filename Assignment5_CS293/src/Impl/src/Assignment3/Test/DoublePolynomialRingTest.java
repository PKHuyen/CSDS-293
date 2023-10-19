package Assignment3.Test;

import Assignment2.*;
import Assignment3.Polynomial;
import Assignment3.PolynomialRing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Test PolynomialRing<Double> */
public class DoublePolynomialRingTest {
    private Ring<Double> doubleRing = new DoubleRing();
    private PolynomialRing<Double> doublePolynomialRing= PolynomialRing.instance(doubleRing);
    private Polynomial<Double> doublePolynomial = Polynomial.from(Collections.emptyList());

    /** Test Zero with Polynomial */
    @Test
    void zeroTest() {
        assertEquals(Collections.emptyList(), doublePolynomialRing.zero().getList());
    }

    /** Test identity with Polynomial */
    @Test
    void identityTest() {
        assertEquals(Collections.singletonList(doubleRing.identity()), doublePolynomialRing.identity().getList());
    }

    /** Test sum with Ring */
    @Test
    void sumTestWithSameSize() {
        Polynomial<Double> ring1 = doublePolynomial.from(List.of(0.0, 1.56, 3.75));
        Polynomial<Double> ring2 = doublePolynomial.from(List.of(100.298372, 200.5, 300.0));
        assertEquals(List.of(100.298372, 202.06, 303.75), doublePolynomialRing.sum(ring1, ring2).getList());

    }
    @Test
    void sumTestWithThisLargerSize() {
        Polynomial<Double> ring1 = doublePolynomial.from(List.of(0.0, 1.56, 3.75));
        Polynomial<Double> ring2 = doublePolynomial.from(List.of(100.298372, 200.5));
        assertEquals(List.of(100.298372, 202.06, 3.75), doublePolynomialRing.sum(ring1, ring2).getList());
    }

    @Test
    void sumTestWithThisSmallerSize() {
        Polynomial<Double> ring1 = doublePolynomial.from(List.of(0.0, 1.56, 3.75));
        Polynomial<Double> ring2 = doublePolynomial.from(List.of(100.298372, 300.0));
        assertEquals(List.of(100.298372, 301.56, 3.75), doublePolynomialRing.sum(ring1, ring2).getList());
    }

    @Test
    void sumTestWithThisNotNull() {
        String errorNotification = "Null found in sum() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> doublePolynomialRing.sum(doublePolynomial.from(
                List.of(0.0, 1.56, 3.75)), null));
        assertEquals(errorNotification, exception.getMessage());
    }

    @Test
    void sumTestWithThisNull() {
        String errorNotification = "Null found in sum() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> doublePolynomialRing.sum(null, doublePolynomial.from(
                List.of(0.0, 1.56, 3.75))));
        assertEquals(errorNotification, exception.getMessage());
    }

    @Test
    void sumTestWithNull() {
        String errorNotification = "Null found in sum() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> doublePolynomialRing.sum(null, null));
        assertEquals(errorNotification, exception.getMessage());
    }

    /** Test Product */
    @Test
    void productTestWithSameSize() {
        Polynomial<Double> ring1 = doublePolynomial.from(List.of(1.0, 2.0, 3.0));
        Polynomial<Double> ring2 = doublePolynomial.from(List.of(4.0, 5.0, 6.0));
        assertEquals(List.of(4.0, 13.0, 28.0, 27.0, 18.0), doublePolynomialRing.product(ring1, ring2).getList());

    }

    @Test
    void productTestWithThisLargerSize() {
        Polynomial<Double> ring1 = doublePolynomial.from(List.of(1.0, 2.0, 3.0));
        Polynomial<Double> ring2 = doublePolynomial.from(List.of(4.0, 5.0));
        assertEquals(List.of(4.0, 13.0, 22.0, 15.0), doublePolynomialRing.product(ring1, ring2).getList());
    }

    @Test
    void productTestWithThisSmallerSize() {
        Polynomial<Double> ring1 = doublePolynomial.from(List.of(1.0, 2.0));
        Polynomial<Double> ring2 = doublePolynomial.from(List.of(3.0, 4.0, 5.0));
        assertEquals(List.of(3.0, 10.0, 13.0, 10.0), doublePolynomialRing.product(ring1, ring2).getList());
    }

    @Test
    void productTestWithThisNotNull() {
        String errorNotification = "Null found in product() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> doublePolynomialRing.product(doublePolynomial.from(
                List.of(1.0, 2.0)), null));
        assertEquals(errorNotification, exception.getMessage());
    }

    @Test
    void productTestWithThisNull() {
        String errorNotification = "Null found in product() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> doublePolynomialRing.product(null, doublePolynomial.from(
                List.of(1.0, 2.0))));
        assertEquals(errorNotification, exception.getMessage());
    }

    @Test
    void productTestWithNull() {
        String errorNotification = "Null found in product() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> doublePolynomialRing.product(null, null));
        assertEquals(errorNotification, exception.getMessage());
    }
}
