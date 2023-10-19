package Assignment2.Test;

import Assignment2.IntegerRing;
import Assignment2.Ring;
import Assignment2.Rings;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Test for Assignment2.IntegerRing on 4 methods
 * zero(), identity(), sum(), product()
 * */
class IntegerRingTest {
    /** Test zero() that return additive identity */
    @Test
    void zeroIntegerTest() {
        Ring<Integer> ring = new IntegerRing();
        assertEquals(0, ring.zero());
    }

    /** Test identity() that return multiplicative identity */
    @Test
    void identityIntegerTest() {
        Ring<Integer> ring = new IntegerRing();
        assertEquals(1, ring.identity());
    }

    /** Test sum() that return sum of param x and param y */
    @Test
    void sumIntegerTest() {
        String errorRing = "Null found in input: sum() - Assignment2.IntegerRing";
        String errorRings = "Null found in input: sum() - Assignment2.Rings";

        /**  Test Assignment2.Ring  */
        Ring<Integer> ring = new IntegerRing();
        /** Valid answer */
        assertEquals(100, ring.sum(0, 100));
        assertEquals(101, ring.sum(1, 100));
        assertEquals(0, ring.sum(1, -1));
        assertEquals(-2, ring.sum(-1, -1));

        /** Throw exception when y = null*/
        Throwable exception = assertThrows(NullPointerException.class, () -> ring.sum(1, null));
        assertEquals(errorRing, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> ring.sum(null, 1));
        assertEquals(errorRing, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> ring.sum(null, null));
        assertEquals(errorRing, exception.getMessage());

        /**  Test RINGS  */
        Rings<Integer> rings = new Rings<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(-1);
        list.add(100000);
        assertEquals(100000, rings.sum(list, ring));

        /** Throw exeption when paramenters is null */
        exception = assertThrows(NullPointerException.class, () -> rings.sum(list, null));
        assertEquals(errorRings, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> rings.sum(null, ring));
        assertEquals(errorRings, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> rings.sum(null, null));
        assertEquals(errorRings, exception.getMessage());
    }

    /** Test product() that return the product of param x and param y */
    @Test
    void productIntegerTest() {
        String errorRing = "Null found in input: product() - Assignment2.IntegerRing";
        String errorRings = "Null found in input: product() - Assignment2.Rings";
        Ring<Integer> ring = new IntegerRing();

        /** Valid Answer */
        assertEquals(0, ring.product(0, 100));
        assertEquals(100, ring.product(1, 100));
        assertEquals(-1, ring.product(1, -1));
        assertEquals(1, ring.product(1, 1));

        /** Throw exception when parameters is null */
        Throwable exception = assertThrows(NullPointerException.class, () -> ring.product(1, null));
        assertEquals(errorRing, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> ring.product(null, 1));
        assertEquals(errorRing, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> ring.product(null, null));
        assertEquals(errorRing, exception.getMessage());

        /**  Test RINGS  */
        Rings<Integer> rings = new Rings<>();
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(1);
        list.add(-1);
        list.add(100000);
        assertEquals(-1000000, rings.product(list, ring));

        /** Throw exeption when paramenters is null */
        exception = assertThrows(NullPointerException.class, () -> rings.product(list, null));
        assertEquals(errorRings, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> rings.product(null, ring));
        assertEquals(errorRings, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> rings.product(null, null));
        assertEquals(errorRings, exception.getMessage());
    }
}