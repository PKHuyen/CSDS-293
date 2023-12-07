package sparse;

import Assignment2.IntegerRing;
import Assignment2.Ring;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixMapRingTest {
    @Test
    public void testZero() {
        Ring<Integer> ring = new IntegerRing(); // Assuming IntegerRing is a Ring<Integer> implementation
        MatrixMapRing<Integer> matrixMapRing = new MatrixMapRing<>(ring);

        MatrixMap<Integer> zeroMatrix = matrixMapRing.zero();
        assertEquals(zeroMatrix.toString(), MatrixMap.from(new Object[1][1]).toString());
    }

    @Test
    public void testIdentity() {
        Ring<Integer> ring = new IntegerRing();
        MatrixMapRing<Integer> matrixMapRing = new MatrixMapRing<>(ring);
        MatrixMap<Integer> identityMatrix = matrixMapRing.identity();
        assertEquals(identityMatrix.toString(), MatrixMap.identity(1, ring.zero(), ring.identity()).toString());
    }

    @Test
    public void testSum() {
        Ring<Integer> ring = new IntegerRing(); // Assuming IntegerRing is a Ring<Integer> implementation
        MatrixMapRing<Integer> matrixMapRing = new MatrixMapRing<>(ring);
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        MatrixMap<Integer> matrix1 = MatrixMap.from(list1);
        MatrixMap<Integer> sumResult = matrixMapRing.sum(matrix1, matrix1);

        assertEquals("Indexes[row=0, col=0]2\n" +
                "Indexes[row=1, col=1]2\n" +
                "Indexes[row=2, col=2]2\n" +
                "Indexes[row=0, col=1]0\n" +
                "Indexes[row=1, col=2]0\n" +
                "Indexes[row=0, col=2]0\n" +
                "Indexes[row=2, col=0]0\n" +
                "Indexes[row=1, col=0]0\n" +
                "Indexes[row=2, col=1]0\n",
                sumResult.toString());
    }

    @Test
    public void testSumXNull() {
        Ring<Integer> ring = new IntegerRing(); // Assuming IntegerRing is a Ring<Integer> implementation
        MatrixMapRing<Integer> matrixMapRing = new MatrixMapRing<>(ring);
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        MatrixMap<Integer> matrix1 = MatrixMap.from(list1);
        String message = "Null found in sum() - MatrixMapRing";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                matrixMapRing.sum(matrix1, null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testSumYNull() {
        Ring<Integer> ring = new IntegerRing(); // Assuming IntegerRing is a Ring<Integer> implementation
        MatrixMapRing<Integer> matrixMapRing = new MatrixMapRing<>(ring);
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        MatrixMap<Integer> matrix1 = MatrixMap.from(list1);
        String message = "Null found in sum() - MatrixMapRing";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                matrixMapRing.sum(null, matrix1));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testProduct() {
        Ring<Integer> ring = new IntegerRing(); // Assuming IntegerRing is a Ring<Integer> implementation
        MatrixMapRing<Integer> matrixMapRing = new MatrixMapRing<>(ring);
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        MatrixMap<Integer> matrix1 = MatrixMap.from(list1);

        assertEquals("Indexes[row=0, col=0]1\n" +
                "Indexes[row=1, col=1]1\n" +
                "Indexes[row=0, col=1]0\n" +
                "Indexes[row=1, col=0]0\n", matrix1.times(matrix1, ring).toString());
    }
    @Test
    public void testProductXNull() {
        Ring<Integer> ring = new IntegerRing(); // Assuming IntegerRing is a Ring<Integer> implementation
        MatrixMapRing<Integer> matrixMapRing = new MatrixMapRing<>(ring);
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        MatrixMap<Integer> matrix1 = MatrixMap.from(list1);
        String message = "Null found in product() - MatrixMapRing";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                matrixMapRing.product(matrix1, null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testProductYNull() {
        Ring<Integer> ring = new IntegerRing(); // Assuming IntegerRing is a Ring<Integer> implementation
        MatrixMapRing<Integer> matrixMapRing = new MatrixMapRing<>(ring);
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        MatrixMap<Integer> matrix1 = MatrixMap.from(list1);
        String message = "Null found in product() - MatrixMapRing";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                matrixMapRing.product(null, matrix1));
        assertEquals(message, exception.getMessage());
    }
}
