package Assignment2.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import Assignment2.BigIntegerRing;
import Assignment2.Ring;
import Assignment2.Rings;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Test for BigInteger on 4 methods
 * zero(), identity(), sum(), product()
 * */
class BigIntegerRingTest {

    /** Test zero() that return additive identity */
    @Test
    void zeroBigIntegerTest() {
        Ring<BigInteger> ring = new BigIntegerRing();
        assertEquals(BigInteger.ZERO, ring.zero());
    }

    /** Test identity() that return multiplicative identity */
    @Test
    void identityBigIntegerTest() {
        Ring<BigInteger> ring = new BigIntegerRing();
        assertEquals(BigInteger.ONE, ring.identity());
    }

    /** Test sum() that return sum of param x and param y */
    @Test
    void sumBigIntegerTest() {
        String errorRing = "Null found in input: sum() - Assignment2.BigIntegerRing";
        String errorRings = "Null found in input: sum() - Assignment2.Rings";
        Ring<BigInteger> ring = new BigIntegerRing();
        /** Valid answer */
        assertEquals(new BigInteger("100"), ring.sum(BigInteger.ZERO, new BigInteger("100")));
        assertEquals(new BigInteger("101"), ring.sum(BigInteger.ONE, new BigInteger("100")));
        assertEquals(BigInteger.ZERO, ring.sum(BigInteger.ONE, new BigInteger("-1")));
        assertEquals(new BigInteger("-2"), ring.sum(new BigInteger("-1"), new BigInteger("-1")));
        assertEquals(new BigInteger("1000000000000"), ring.sum(new BigInteger("-1000000000000"), new BigInteger("2000000000000")));

        /** Throw exception when null found */
        Throwable exception = assertThrows(NullPointerException.class, () -> ring.sum(BigInteger.ONE, null));
        assertEquals(errorRing, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> ring.sum(null, BigInteger.ONE));
        assertEquals(errorRing, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> ring.sum(null, null));
        assertEquals(errorRing, exception.getMessage());

        /**  TEST RINGS  */
        Rings<BigInteger> rings = new Rings<>();
        List<BigInteger> list = new ArrayList<>();
        list.add(BigInteger.ZERO);
        list.add(BigInteger.ONE);
        list.add(new BigInteger("-1"));
        list.add(new BigInteger("1000000000"));
        assertEquals(new BigInteger("1000000000"), rings.sum(list, ring));

        /** Throw exception when null found */
        exception = assertThrows(NullPointerException.class, () -> rings.sum(list, null));
        assertEquals(errorRings, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> rings.sum(null, ring));
        assertEquals(errorRings, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> rings.sum(null, null));
        assertEquals(errorRings, exception.getMessage());
    }

    /** Test product() that return the product of param x and param y */
    @Test
    void productBigIntegerTest() {
        String error = "Null found in input: product() - Assignment2.BigIntegerRing";
        String errorRings = "Null found in input: product() - Assignment2.Rings";

        Ring<BigInteger> ring = new BigIntegerRing();

        /** Valid Answer */
        assertEquals(new BigInteger("0"), ring.product(BigInteger.ZERO, new BigInteger("100")));
        assertEquals(new BigInteger("100"), ring.product(BigInteger.ONE, new BigInteger("100")));
        assertEquals(new BigInteger("-1"), ring.product(BigInteger.ONE, new BigInteger("-1")));
        assertEquals(new BigInteger("1"), ring.product(BigInteger.ONE, BigInteger.ONE));

        /** Throw exception when y = null*/
        Throwable exception = assertThrows(NullPointerException.class, () -> ring.product(BigInteger.ONE, null));
        assertEquals(error, exception.getMessage());

        /** Throw Exception when x = null */
        exception = assertThrows(NullPointerException.class, () -> ring.product(null, BigInteger.ONE));
        assertEquals(error, exception.getMessage());

        /** Throw Exception when x = null & y = null */
        exception = assertThrows(NullPointerException.class, () -> ring.product(null, null));
        assertEquals(error, exception.getMessage());

        /**  TEST RINGS  */
        Rings<BigInteger> rings = new Rings<>();
        List<BigInteger> list = new ArrayList<>();
        list.add(new BigInteger("2"));
        list.add(BigInteger.ONE);
        list.add(new BigInteger("-1"));
        list.add(new BigInteger("1000000000"));
        assertEquals(new BigInteger("-2000000000"), rings.product(list, ring));

        /** Throw exception when null found */
        exception = assertThrows(NullPointerException.class, () -> rings.product(list, null));
        assertEquals(errorRings, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> rings.product(null, ring));
        assertEquals(errorRings, exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> rings.product(null, null));
        assertEquals(errorRings, exception.getMessage());
    }
}