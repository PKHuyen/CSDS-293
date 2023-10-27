import Ring.IntegerRing;
import Ring.Ring;
import Ring.Rings;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/** Test for Assignment2.IntegerRing on 4 methods
 * zero(), identity(), sum(), product()
 * */
public class IntegerRingTest {
    /**
     * Test zero() that return additive identity
     */
    @Test
    public void zeroIntegerRingTest() {
        Ring<Integer> ring = new IntegerRing();
        assertEquals((int) 0, (int) ring.zero());
    }

    /**
     * Test identity() that return multiplicative identity
     */
    @Test
    public void identityIntegerRingTest() {
        Ring<Integer> ring = new IntegerRing();
        assertEquals((int) 1, (int) ring.identity());
    }

    /**
     * Test sum() that return sum of param x and param y
     */
    @Test
    public void sumIntegerPositivePositiveRingTest() {
        /**  Test Assignment2.Ring  */
        Ring<Integer> ring = new IntegerRing();
        assertEquals((int) 101, (int) ring.sum(1, 100));
    }

    /** Good data */
    @Test
    public void sumIntegerPositiveNegativeRingTest() {
        Ring<Integer> ring = new IntegerRing();
        assertEquals((int) 0, (int) ring.sum(1, -1));

    }

    /** Good data */
    @Test
    public void sumIntegerNegativeNegativeRingTest() {
        Ring<Integer> ring = new IntegerRing();
        assertEquals((int) -2, (int) ring.sum(-1, -1));
    }

    /** Good data */
    @Test
    public void sumIntegerRingXNull() {
        Ring<Integer> ring = new IntegerRing();
        String errorRing = "Null found in input: sum() - Assignment2.IntegerRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> ring.sum(null, 1));
        assertEquals(errorRing, exception.getMessage());
    }

    /** Good data */
    @Test
    public void sumIntegerRingYNull() {
        Ring<Integer> ring = new IntegerRing();
        String errorRing = "Null found in input: sum() - Assignment2.IntegerRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> ring.sum(1, null));
        assertEquals(errorRing, exception.getMessage());
    }

    /** Good data */
    @Test
    public void sumIntegerRingNull() {
        Ring<Integer> ring = new IntegerRing();
        String errorRing = "Null found in input: sum() - Assignment2.IntegerRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> ring.sum(null, null));
        assertEquals(errorRing, exception.getMessage());
    }

    /** Good data */
    @Test
    public void sumIntegerRingsTest() {
        Ring<Integer> ring = new IntegerRing();
        Rings<Integer> rings = new Rings();
        assertEquals((int)6, (int)rings.sum(List.of(1,2,3), ring));
    }

    /** Good data */
    @Test
    public void sumIntegerRingsEmpty() {
        Ring<Integer> ring = new IntegerRing();
        Rings<Integer> rings = new Rings();
        List<Integer> list = List.of();
        assertEquals(ring.zero(), rings.sum(list, ring));
    }

    /** Good data */
    @Test
    public void sumIntegerRingListNullTest() {
        Ring<Integer> ring = new IntegerRing();
        Rings<Integer> rings = new Rings();
        String errorRings = "Null found in input: sum() - Assignment2.Rings";
        Throwable exception = assertThrows(NullPointerException.class, () -> rings.sum(null, ring));
        assertEquals(errorRings, exception.getMessage());
    }

    /** Good data */
    @Test
    public void sumIntegerRingRingNullTest() {
        Rings<Integer> rings = new Rings();
        String errorRings = "Null found in input: sum() - Assignment2.Rings";
        Throwable exception = assertThrows(NullPointerException.class, () -> rings.sum(List.of(1,2,3), null));
        assertEquals(errorRings, exception.getMessage());
    }

    /** Good data */
    @Test
    public void sumIntegerRingNullTest() {
        Rings<Integer> rings = new Rings();
        String errorRings = "Null found in input: sum() - Assignment2.Rings";
        Throwable exception = assertThrows(NullPointerException.class, () -> rings.sum(null, null));
        assertEquals(errorRings, exception.getMessage());
    }

    /** Good data */
    @Test
    public void productIntegerPositivePositiveRingTest() {
        /**  Test Assignment2.Ring  */
        Ring<Integer> ring = new IntegerRing();
        assertEquals((int) 100, (int) ring.product(1, 100));
    }

    /** Good data */
    @Test
    public void productIntegerPositiveNegativeRingTest() {
        Ring<Integer> ring = new IntegerRing();
        assertEquals((int) -1, (int) ring.product(1, -1));
    }

    /** Good data */
    @Test
    public void productIntegerNegativeNegativeRingTest() {
        Ring<Integer> ring = new IntegerRing();
        assertEquals((int) 1, (int) ring.product(-1, -1));
    }

    /** Good data */
    @Test
    public void productIntegerRingXNull() {
        Ring<Integer> ring = new IntegerRing();
        String errorRing = "Null found in input: product() - Assignment2.IntegerRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> ring.product(null, 1));
        assertEquals(errorRing, exception.getMessage());
    }

    /** Good data */
    @Test
    public void productIntegerRingYNull() {
        Ring<Integer> ring = new IntegerRing();
        String errorRing = "Null found in input: product() - Assignment2.IntegerRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> ring.product(1, null));
        assertEquals(errorRing, exception.getMessage());
    }

    /** Good data */
    @Test
    public void productIntegerRingNull() {
        Ring<Integer> ring = new IntegerRing();
        String errorRing = "Null found in input: product() - Assignment2.IntegerRing";
        Throwable exception = assertThrows(NullPointerException.class, () -> ring.product(null, null));
        assertEquals(errorRing, exception.getMessage());
    }

    /** Good data */
    @Test
    public void productIntegerRingsTest() {
        Ring<Integer> ring = new IntegerRing();
        Rings<Integer> rings = new Rings();
        assertEquals((int)6, (int)rings.product(List.of(1,2,3), ring));
    }

    /** Good data */
    @Test
    public void productIntegerRingsEmpty() {
        Ring<Integer> ring = new IntegerRing();
        Rings<Integer> rings = new Rings();
        List<Integer> list = List.of();
        assertEquals(ring.identity(), rings.product(list, ring));
    }

    /** Good data */
    @Test
    public void productIntegerRingListNullTest() {
        Ring<Integer> ring = new IntegerRing();
        Rings<Integer> rings = new Rings();
        String errorRings = "Null found in input: product() - Assignment2.Rings";
        Throwable exception = assertThrows(NullPointerException.class, () -> rings.product(null, ring));
        assertEquals(errorRings, exception.getMessage());
    }

    /** Good data */
    @Test
    public void productIntegerRingsRingNullTest() {
        Rings<Integer> rings = new Rings();
        String errorRings = "Null found in input: product() - Assignment2.Rings";
        Throwable exception = assertThrows(NullPointerException.class, () -> rings.product(List.of(1,2,3), null));
        assertEquals(errorRings, exception.getMessage());
    }

    /** Good data */
    @Test
    public void productIntegerRingsNullTest() {
        Rings<Integer> rings = new Rings();
        String errorRings = "Null found in input: product() - Assignment2.Rings";
        Throwable exception = assertThrows(NullPointerException.class, () -> rings.product(null, null));
        assertEquals(errorRings, exception.getMessage());
    }
}