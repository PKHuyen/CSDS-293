package sparse;

import Assignment2.IntegerRing;
import Assignment2.Ring;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SparseMatrixRingTest {
    @Test
    public void testZero() {
        Ring<Integer> ring = new IntegerRing(); // Assuming IntegerRing is a Ring<Integer> implementation
        SparseMatrixRing<Integer> sparseMatrixRing = new SparseMatrixRing<>(ring);

        SparseMatrix<Integer> zeroMatrix = sparseMatrixRing.zero();
        assertEquals(zeroMatrix.toString(), SparseMatrix.from(new Object[1][1]).toString());
    }

    @Test
    public void testIdentity() {
        Ring<Integer> ring = new IntegerRing();
        SparseMatrixRing<Integer> sparseMatrixRing = new SparseMatrixRing<>(ring);
        SparseMatrix<Integer> identityMatrix = sparseMatrixRing.identity();
        assertEquals(identityMatrix.toString(), SparseMatrix.identity(1, ring.zero(), ring.identity()).toString());
    }

    @Test
    public void testSum() {
        Ring<Integer> ring = new IntegerRing(); // Assuming IntegerRing is a Ring<Integer> implementation
        SparseMatrixRing<Integer> sparseMatrixRing = new SparseMatrixRing<>(ring);
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        SparseMatrix<Integer> matrix1 = SparseMatrix.from(list1);
        SparseMatrix<Integer> sumResult = sparseMatrixRing.sum(matrix1, matrix1);

        assertEquals("Indexes[row=0, col=0]2\n" +
                        "Indexes[row=1, col=1]2\n" +
                        "Indexes[row=2, col=2]2\n"
                        ,
                sumResult.toString());
    }

    @Test
    public void testSumXNull() {
        Ring<Integer> ring = new IntegerRing(); // Assuming IntegerRing is a Ring<Integer> implementation
        SparseMatrixRing<Integer> sparseMatrixRing = new SparseMatrixRing<>(ring);
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        SparseMatrix<Integer> matrix1 = SparseMatrix.from(list1);
        String message = "Null found in sum() - SparseMatrixRing";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                sparseMatrixRing.sum(matrix1, null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testSumYNull() {
        Ring<Integer> ring = new IntegerRing(); // Assuming IntegerRing is a Ring<Integer> implementation
        SparseMatrixRing<Integer> sparseMatrixRing = new SparseMatrixRing<>(ring);
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        SparseMatrix<Integer> matrix1 = SparseMatrix.from(list1);
        String message = "Null found in sum() - SparseMatrixRing";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                sparseMatrixRing.sum(null, matrix1));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testProduct() {
        Ring<Integer> ring = new IntegerRing(); // Assuming IntegerRing is a Ring<Integer> implementation
        SparseMatrixRing<Integer> sparseMatrixRing = new SparseMatrixRing<>(ring);
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        SparseMatrix<Integer> matrix1 = SparseMatrix.from(list1);

        assertEquals("Indexes[row=0, col=0]1\n" +
                "Indexes[row=1, col=1]1\n" +
                "Indexes[row=0, col=1]0\n" +
                "Indexes[row=1, col=0]0\n", matrix1.times(matrix1, ring).toString());
    }

    @Test
    public void testProductXNull() {
        Ring<Integer> ring = new IntegerRing(); // Assuming IntegerRing is a Ring<Integer> implementation
        SparseMatrixRing<Integer> sparseMatrixRing = new SparseMatrixRing<>(ring);
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        SparseMatrix<Integer> matrix1 = SparseMatrix.from(list1);
        String message = "Null found in product() - SparseMatrixRing";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                sparseMatrixRing.product(matrix1, null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testProductYNull() {
        Ring<Integer> ring = new IntegerRing(); // Assuming IntegerRing is a Ring<Integer> implementation
        SparseMatrixRing<Integer> sparseMatrixRing = new SparseMatrixRing<>(ring);
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        SparseMatrix<Integer> matrix1 = SparseMatrix.from(list1);
        String message = "Null found in product() - SparseMatrixRing";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                sparseMatrixRing.product(null, matrix1));
        assertEquals(message, exception.getMessage());
    }
}
