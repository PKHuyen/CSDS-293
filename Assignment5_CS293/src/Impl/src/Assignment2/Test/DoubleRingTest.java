package Assignment2.Test;

import Assignment2.DoubleRing;
import Assignment2.Ring;
import Assignment2.Rings;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Test for Assignment2.DoubleRing on 4 methods
 * zero(), identity(), sum(), product()
 * */

class DoubleRingTest {
    /** Test zero() that return additive identity */
    @Test
    void zeroDoubleTest() {
        Ring<Double> ring = new DoubleRing();
        assertEquals(0.0, ring.zero());
    }

    /** Test identity() that return multiplicative identity */
    @Test
    void identityDoubleTest() {
        Ring<Double> ring = new DoubleRing();
        assertEquals(1.0, ring.identity());
    }

    /** Test sum() that return sum of param x and param y */
    @Test
    void sumDoubleTest() {
        String error = "Null found in input: sum() - Assignment2.DoubleRing";
        Ring<Double> ring = new DoubleRing();
        /** Valid answer */
        assertEquals(100.0, ring.sum(0.0, 100.0));
        assertEquals(102.2, ring.sum(1.5, 100.7));
        assertEquals(0.0, ring.sum(1.0, -1.0));
        assertEquals(-2.0, ring.sum(-1.0, -1.0));
        assertEquals(1000000000000.0, ring.sum(-1000000000000.0, 2000000000000.0));

        /** Throw exception when y = null*/
        Throwable exception = assertThrows(NullPointerException.class, () -> ring.sum(1.0, null));
        assertEquals(error, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> ring.sum(null, 1.0));
        assertEquals(error, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> ring.sum(null, null));
        assertEquals(error, exception.getMessage());

        /**  TEST RINGS  */
        String errorRings = "Null found in input: sum() - Assignment2.Rings";
        Rings<Double> rings = new Rings();
        List<Double> list = new ArrayList<>();
        list.add(10.2);
        list.add(1.5);
        list.add(-1.7);
        list.add(100000.532426846264);
        assertEquals(100010.532426846264, rings.sum(list, ring));

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
    void productDoubleTest() {
        String error = "Null found in input: product() - Assignment2.DoubleRing";
        Ring<Double> ring = new DoubleRing();

        /** Valid Answer */
        assertEquals(0.0, ring.product(0.0, 100.0));
        assertEquals(150.0, ring.product(1.5, 100.0));
        assertEquals(-1.0, ring.product(1.0, -1.0));
        assertEquals(1.0, ring.product(1.0, 1.0));

        /** Throw exception when y = null*/
        Throwable exception = assertThrows(NullPointerException.class, () -> ring.product(1.0, null));
        assertEquals(error, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> ring.product(null, 1.0));
        assertEquals(error, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> ring.product(null, null));
        assertEquals(error, exception.getMessage());

        /**  TEST RINGS  */
        String errorRings = "Null found in input: product() - Assignment2.Rings";
        Rings<Double> rings = new Rings();
        List<Double> list = new ArrayList<>();
        list.add(10.2);
        list.add(1.5);
        list.add(-1.7);
        list.add(100000.532426846264);
        assertEquals((10.2*1.5*-1.7*100000.532426846264), rings.product(list, ring));

        /** Throw exeption when paramenters is null */
        exception = assertThrows(NullPointerException.class, () -> rings.product(list, null));
        assertEquals(errorRings, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> rings.product(null, ring));
        assertEquals(errorRings, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> rings.product(null, null));
        assertEquals(errorRings, exception.getMessage());

    }
}