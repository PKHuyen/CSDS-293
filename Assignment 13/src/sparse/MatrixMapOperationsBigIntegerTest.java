package sparse;
import Assignment2.BigIntegerRing;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class MatrixMapOperationsBigIntegerTest {
    @Test
    public void testPlusSquareMatrix() {
        BigIntegerRing ring = new BigIntegerRing();
        BigInteger[][] list1 = {{new BigInteger("1"), new BigInteger("2")},
                {new BigInteger("3"), new BigInteger("4")}};

        BigInteger[][] result = {{new BigInteger("2"), new BigInteger("4")},
                {new BigInteger("6"), new BigInteger("8")}};


        assertEquals(MatrixMap.from(result).toString(),
                MatrixMap.from(list1).plus(MatrixMap.from(list1), (x, y) -> ring.sum(x,y)).toString());
    }

    @Test
    public void testPlusNonSquareMatrix() {
        BigInteger[][] list1 = {{new BigInteger("1"), new BigInteger("2"), new BigInteger("3")},
                {new BigInteger("4"), new BigInteger("5"), new BigInteger("6")}};
        BigInteger[][] result = {{new BigInteger("2"), new BigInteger("4"), new BigInteger("6")},
                {new BigInteger("8"), new BigInteger("10"), new BigInteger("12")}};
        BigIntegerRing ring = new BigIntegerRing();
        assertEquals(MatrixMap.from(result).toString(),
                MatrixMap.from(list1).plus(MatrixMap.from(list1), (x, y) -> ring.sum(x,y)).toString());
    }

    @Test
    public void testPlusNull() {
        BigIntegerRing ring = new BigIntegerRing();
        BigInteger[][] list1 = {{new BigInteger("1"), new BigInteger("2"), new BigInteger("3")},
                {new BigInteger("4"), new BigInteger("5"), new BigInteger("6")}};
        String message = "Null found in plus - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, ()
                -> MatrixMap.from(list1).plus(null, (x, y) -> ring.sum(x,y)).toString());
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testMultiplySquareMatrix() {
        BigIntegerRing ring = new BigIntegerRing();
        BigInteger[][] list1 = {{new BigInteger("1"), new BigInteger("2")},
                {new BigInteger("3"), new BigInteger("4")}};

        BigInteger[][] result = {{new BigInteger("1")}};

        assertEquals(MatrixMap.from(result).toString(),
                MatrixMap.from(list1).times(MatrixMap.from(list1), ring).toString());
    }

    @Test
    public void testMultiplyNull() {
        BigIntegerRing ring = new BigIntegerRing();
        BigInteger[][] list1 = {{new BigInteger("1"), new BigInteger("2"), new BigInteger("3")},
                {new BigInteger("4"), new BigInteger("5"), new BigInteger("6")}};
        String message = "Null found in times() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, ()
                -> MatrixMap.from(list1).times(null, ring).toString());
        assertEquals(message, exception.getMessage());
    }
}