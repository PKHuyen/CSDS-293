import Ring.IntegerRing;
import Ring.Ring;
import Polynomial.Polynomial;
import Polynomial.PolynomialRing;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.*;

/** Test the PolynomialRing<Polynomial<Integer>> */
public class IntegerPolynomialRingTest {
    /** Used for public methods testing */
    private Ring<Polynomial<Integer>> integerPolynomial = PolynomialRing.instance(new IntegerRing());
    private PolynomialRing<Polynomial<Integer>> integerPolynomialRing = PolynomialRing.instance(integerPolynomial);

    /** Used for private methods testing*/
    private Ring<Integer>  integerRing = new IntegerRing();
    private PolynomialRing<Integer> polynomialRing = PolynomialRing.instance(integerRing);

    /** Test zero() */
    @Test
    public void zeroTest() {
        assertEquals(Collections.emptyList(), integerPolynomialRing.zero().getList());
    }

    /** Test identity() */
    @Test
    public void identityTest() {
        assertEquals(Arrays.toString(List.of(integerPolynomial.identity()).toArray()), integerPolynomialRing.identity().toString());
    }

    /** Test sum() */
    @Test
    public void sumTestWithSameSize() {
        Polynomial<Polynomial<Integer>> p1 = Polynomial.from(List.of(Polynomial.from(List.of(0, 1, 3))));
        Polynomial<Polynomial<Integer>> p2 = Polynomial.from(List.of(Polynomial.from(List.of(100, 200, 300))));
        Polynomial<Polynomial<Integer>> res = Polynomial.from(List.of(Polynomial.from(List.of(100,201,303))));
        assertEquals(Arrays.toString(res.getList().toArray()), integerPolynomialRing.sum(p1, p2).toString());
    }

    /** Good data */
    @Test
    public void sumTestWithThisLargerSize() {
        Polynomial<Polynomial<Integer>> p1 = Polynomial.from(List.of(Polynomial.from(List.of(0, 1, 3))));
        Polynomial<Polynomial<Integer>> p2 = Polynomial.from(List.of(Polynomial.from(List.of(100, 200))));
        Polynomial<Polynomial<Integer>> res = Polynomial.from(List.of(Polynomial.from(List.of(100,201,3))));
        assertEquals(Arrays.toString(res.getList().toArray()), integerPolynomialRing.sum(p1, p2).toString());
    }

    /** Good data */
    @Test
    public void sumTestWithThisSmallerSize() {
        Polynomial<Polynomial<Integer>> p1 = Polynomial.from(List.of(Polynomial.from(List.of(0, 1))));
        Polynomial<Polynomial<Integer>> p2 = Polynomial.from(List.of(Polynomial.from(List.of(100, 200, 300))));
        Polynomial<Polynomial<Integer>> res = Polynomial.from(List.of(Polynomial.from(List.of(100,201,300))));
        assertEquals(Arrays.toString(res.getList().toArray()), integerPolynomialRing.sum(p1, p2).toString());
    }

    /** Good data */
    @Test
    public void sumTestWithThisNotNull() {
        String errorNotification = "Null found in sum() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> integerPolynomialRing.sum(
                Polynomial.from(List.of(Polynomial.from(List.of(0,1,3)))), null));
        assertEquals(errorNotification, exception.getMessage());
    }

    /** Good data */
    @Test
    public void sumTestWithThisNull() {
        String errorNotification = "Null found in sum() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> integerPolynomialRing.sum(
                null, Polynomial.from(List.of(Polynomial.from(List.of(0,1,3))))));
        assertEquals(errorNotification, exception.getMessage());
    }

    /** Good data */
    @Test
    public void sumTestWithNull() {
        String errorNotification = "Null found in sum() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> integerPolynomialRing.sum(
                null, null));
        assertEquals(errorNotification, exception.getMessage());
    }

    /** Good data
     * CC1, BR3 */
    @Test
    public void productTestWithSameSize() {
        Polynomial<Polynomial<Integer>> p1 = Polynomial.from(List.of(Polynomial.from(List.of(1, 2, 3))));
        Polynomial<Polynomial<Integer>> p2 = Polynomial.from(List.of(Polynomial.from(List.of(4, 5, 6))));
        Polynomial<Polynomial<Integer>> res = Polynomial.from(List.of(Polynomial.from(List.of(4,13,28,27,18))));
        assertEquals(Arrays.toString(res.getList().toArray()), integerPolynomialRing.product(p1, p2).toString());
    }

    /** Good data
     * CC1, BR3 */
    @Test
    public void productTestWithLargerSize() {
        Polynomial<Polynomial<Integer>> p1 = Polynomial.from(List.of(Polynomial.from(List.of(1, 2, 3))));
        Polynomial<Polynomial<Integer>> p2 = Polynomial.from(List.of(Polynomial.from(List.of(4, 5))));
        Polynomial<Polynomial<Integer>> res = Polynomial.from(List.of(Polynomial.from(List.of(4,13,22,15))));
        assertEquals(Arrays.toString(res.getList().toArray()), integerPolynomialRing.product(p1, p2).toString());
    }

    /** Good data
     * CC1, BR3 */
    @Test
    public void productTestWithSmallerSize() {
        Polynomial<Polynomial<Integer>> p1 = Polynomial.from(List.of(Polynomial.from(List.of(1, 2))));
        Polynomial<Polynomial<Integer>> p2 = Polynomial.from(List.of(Polynomial.from(List.of(4, 5, 6))));
        Polynomial<Polynomial<Integer>> res = Polynomial.from(List.of(Polynomial.from(List.of(4,13,16,12))));
        assertEquals(Arrays.toString(res.getList().toArray()), integerPolynomialRing.product(p1, p2).toString());
    }

    /** Good data
     * CC1, BR2 */
    @Test
    public void productTestWithEmptyList() {
        Polynomial<Polynomial<Integer>> p1 = Polynomial.from(List.of(Polynomial.from(List.of(4,13,16,12))));
        Polynomial<Polynomial<Integer>> p2 = Polynomial.from(Collections.emptyList());
        assertEquals(Arrays.toString(p2.getList().toArray()), integerPolynomialRing.product(p1, p2).toString());
    }

    /** Good data
     * CC1, BR2 */
    @Test
    public void productTestWithThisNotNull() {
        String errorNotification = "Null found in product() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> integerPolynomialRing.product(
                Polynomial.from(List.of(Polynomial.from(List.of(0,1,3)))), null));
        assertEquals(errorNotification, exception.getMessage());
    }

    /** Good data
     * CC1, BR1 */
    @Test
    public void productTestWithThisNull() {
        String errorNotification = "Null found in product() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> integerPolynomialRing.product(null,
                Polynomial.from(List.of(Polynomial.from(List.of(0,1,3))))));
        assertEquals(errorNotification, exception.getMessage());
    }

    /** Good data
     * CC1, BR1, BR2 */
    @Test
    public void productTestWithNull() {
        String errorNotification = "Null found in product() - PolynomialRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> integerPolynomialRing.product(
                null, null));
        assertEquals(errorNotification, exception.getMessage());
    }

    /** Good data */
    @Test
    public void testIterable() {
        Polynomial<Polynomial<Integer>> res = Polynomial.from(List.of(Polynomial.from(List.of(4,13,22,15))));
        assertTrue(res.iterator() != null);
    }

    /** Good data */
    @Test
    public void testInverse() {
        Polynomial<Polynomial<Integer>> res = Polynomial.from(List.of(Polynomial.from(List.of(4,13,22,15))));
        assertEquals(List.of(List.of(-4, -13, -22, -15)).toString(),integerPolynomialRing.inverse(res).toString());
    }

    /** Good data */
    @Test
    public void testPlusHelper() {
        Polynomial.HelperMethodTest test1 = new Polynomial.HelperMethodTest();
        Polynomial<Integer> p1 = Polynomial.from(List.of(1,1));
        Polynomial<Integer> p2 = Polynomial.from(List.of(1,1));
        assertEquals((Integer) 1, (Integer)test1.plusHelper(integerRing, p2.getList(), 0));
    }

    /** Good data */
    @Test
    public void testPlusHelperRingNull() {
        Polynomial.HelperMethodTest test = new Polynomial.HelperMethodTest();
        Polynomial<Integer> p2 = Polynomial.from(List.of(1,1));
        assertThrows(AssertionError.class, () -> test.plusHelper(null, p2.getList(), 0));
    }

    /** Good data */
    @Test
    public void testPlusHelperListNull() {
        Polynomial.HelperMethodTest test = new Polynomial.HelperMethodTest();
        assertThrows(AssertionError.class, () -> test.plusHelper(integerRing, null, 0));
    }

    @Test
    /** Test Times Helper
     * CC1, BR4, BR5*/
    public void testTimesHelper() {
        Polynomial.HelperMethodTest test = new Polynomial.HelperMethodTest();
        Polynomial<Integer> p1 = Polynomial.from(List.of(1,1));
        Polynomial<Integer> p2 = Polynomial.from(List.of(1,1));
        assertEquals(List.of(1,2,1), test.timesHelper(p1, integerRing, p2.getList()));
    }

    @Test
    /** Test Times Helper
     * CC1, BR3, BR5 */
    public void testTimesHelperSmallerSize() {
        Polynomial.HelperMethodTest test = new Polynomial.HelperMethodTest();
        Polynomial<Integer> p1 = Polynomial.from(List.of(1,1));
        Polynomial<Integer> p2 = Polynomial.from(List.of(1,2,3));
        assertEquals(List.of(1,3,5,3), test.timesHelper(p1, integerRing, p2.getList()));
    }

    @Test
    /** Test Times Helper
     * CC1, BR4, BR5 */
    public void testTimesHelperLargerSize() {
        Polynomial.HelperMethodTest test = new Polynomial.HelperMethodTest();
        Polynomial<Integer> p1 = Polynomial.from(List.of(1,1));
        Polynomial<Integer> p2 = Polynomial.from(List.of(1,2,3));
        assertEquals(List.of(1,3,5,3), test.timesHelper(p2, integerRing, p1.getList()));
    }

    /** Good data
     * BD1 */
    @Test
    public void testTimesHelperOtherNull() {
        Polynomial.HelperMethodTest test = new Polynomial.HelperMethodTest();
        Polynomial<Integer> p2 = Polynomial.from(List.of(1,1));
        assertThrows(AssertionError.class, () -> test.timesHelper(null, integerRing, p2.getList()));
    }

    /** Good data
     * BD2 */
    @Test
    public void testTimesHelperRingNull() {
        Polynomial.HelperMethodTest test = new Polynomial.HelperMethodTest();
        Polynomial<Integer> p1 = Polynomial.from(List.of(1,1));
        assertThrows(AssertionError.class, () -> test.timesHelper(p1, null, p1.getList()));
    }

    /** Good data */
    @Test
    public void testTimesHelperCoefficientNull() {
        Polynomial.HelperMethodTest test = new Polynomial.HelperMethodTest();
        Polynomial<Integer> p1 = Polynomial.from(List.of(1,1));
        assertThrows(AssertionError.class, () -> test.timesHelper(p1, integerRing, null));
    }

    /** Good data
     * CC1 */
    @Test
    public void testCheck() {
        Polynomial.HelperMethodTest test = new Polynomial.HelperMethodTest();
        Polynomial<Integer> p1 = Polynomial.from(List.of(1,1));
        Polynomial<Integer> p2 = Polynomial.from(List.of(1,1));
        ListIterator l1= p1.listIterator(0);
        ListIterator l2= p2.listIterator(1);
        assertFalse(test.check(l1, l2, List.of(1,1)));
        assertTrue(test.check(l2, l1, List.of(1,1)));
    }

    /** Good data
     * BD1 */
    @Test
    public void testCheckThisNull() {
        Polynomial.HelperMethodTest test = new Polynomial.HelperMethodTest();
        Polynomial<Integer> p1 = Polynomial.from(List.of(1,1));
        Polynomial<Integer> p2 = Polynomial.from(List.of(1,1));
        ListIterator l1= p1.listIterator(0);
        ListIterator l2= p2.listIterator(1);
        assertThrows(AssertionError.class, () -> test.check(null, l1, List.of(1,1)));
    }

    /** Good data
     * BD2 */
    @Test
    public void testCheckOtherNull() {
        Polynomial.HelperMethodTest test = new Polynomial.HelperMethodTest();
        Polynomial<Integer> p1 = Polynomial.from(List.of(1,1));
        Polynomial<Integer> p2 = Polynomial.from(List.of(1,1));
        ListIterator l1= p1.listIterator(0);
        ListIterator l2= p2.listIterator(1);
        assertThrows(AssertionError.class, () -> test.check(l1, null, List.of(1,1)));
    }

    /** Good data */
    @Test
    public void testCheckCoefficientNull() {
        Polynomial.HelperMethodTest test = new Polynomial.HelperMethodTest();
        Polynomial<Integer> p1 = Polynomial.from(List.of(1,1));
        Polynomial<Integer> p2 = Polynomial.from(List.of(1,1));
        ListIterator l1= p1.listIterator(0);
        ListIterator l2= p2.listIterator(1);
        assertThrows(AssertionError.class, () -> test.check(l1, l2, null));
    }
}