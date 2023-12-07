package sparse;

import Assignment2.IntegerRing;
import org.junit.Test;
import sparse.MatrixMap;

import static org.junit.Assert.*;

public class SparseMatrixTest<Indexes, T> {
    @Test
    public void testSize() {
        Integer[][] matrix = new Integer[][]{
                {1, 1, 1, 6, 1},
                {2, 2, 2, 200, 0},
                {3, 3, 3, 82, 0},
                {4, 5, 6, 7, 0},
                {6, 2, 50, 4, 8, 0}
        };
        assertEquals(new sparse.Indexes(4, 4), SparseMatrix.from(matrix).size());
    }

    @Test
    public void testValueIndexes() {
        Integer[][] matrix = new Integer[][]{
                {2, 1, 1},
                {2, -3, 4}
        };
        assertEquals(-3, SparseMatrix.from(matrix).value(new sparse.Indexes(1, 1)).intValue());
    }

    @Test
    public void testValueIndexesNull() {
        String message = "Cannot invoke \"Object.equals(Object)\" because \"value\" is null";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                SparseMatrix.from(new Integer[1][1]).value(null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testValueRowAndCol() {
        Integer[][] matrix = new Integer[][]{
                {1, -2, -3},
                {3, 4, 0},
                {5, -6, 0}
        };
        assertEquals(1, SparseMatrix.from(matrix).value(0, 0).intValue());
    }

    @Test
    public void testInstanceRowsAndColumns() {
        SparseMatrix<Integer> matrixMap = SparseMatrix.instance(3, 2, indexes -> indexes.row() + indexes.col());
        assertEquals(
        "Indexes[row=0, col=1]1\n"+
        "Indexes[row=1, col=0]1\n"+
        "Indexes[row=1, col=1]2\n"+
        "Indexes[row=2, col=0]2\n"+
        "Indexes[row=2, col=1]3\n",
                matrixMap.toString());
    }

    @Test
    public void testInstanceRowsAndColumnsNull() {
        String message = "Null found in instance() - SparseMatrix";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                SparseMatrix.instance(3,2, null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testInstanceRowsAndColumnsOutOfBound() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                SparseMatrix.instance(-3,2, indexes -> indexes.row() + indexes.col()));
        assertEquals(SparseMatrix.InvalidLengthException.class, exception.getCause().getClass());
    }

    @Test
    public void testInstanceSize() {
        SparseMatrix<Integer> matrixMap = SparseMatrix.instance(new sparse.Indexes(2,1), indexes -> indexes.row() + indexes.col());
        assertEquals(
                        "Indexes[row=0, col=1]1\n" +
                        "Indexes[row=1, col=0]1\n" +
                        "Indexes[row=1, col=1]2\n" +
                        "Indexes[row=2, col=0]2\n" +
                        "Indexes[row=2, col=1]3\n",
                matrixMap.toString());
    }

    @Test
    public void testInstanceSizeNull() {
        String message = "Null found in instance() - SparseMatrix";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                SparseMatrix.instance(new sparse.Indexes(3,2), null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testInstanceSizeOutOfBound() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                SparseMatrix.instance(new sparse.Indexes(-3,2), indexes -> indexes.row() + indexes.col()));
        assertEquals(SparseMatrix.InvalidLengthException.class, exception.getCause().getClass());
    }

    @Test
    public void testConstant() {
        SparseMatrix<Integer> map1 = SparseMatrix.constant(2, 10);
        assertEquals(
                "Indexes[row=0, col=0]10\n" +
                        "Indexes[row=0, col=1]10\n" +
                        "Indexes[row=1, col=0]10\n" +
                        "Indexes[row=1, col=1]10\n",
                map1.toString());

        SparseMatrix<Integer> matrixMap2 = SparseMatrix.constant(2, 2000);
        assertEquals("Indexes[row=0, col=0]2000\n" +
                        "Indexes[row=0, col=1]2000\n" +
                        "Indexes[row=1, col=0]2000\n" +
                        "Indexes[row=1, col=1]2000\n",
                matrixMap2.toString());
    }

    @Test
    public void testConstantNull() {
        String message = "Null found in constant() - SparseMatrix";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                SparseMatrix.constant(3, null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testConstantOutOfBound() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                SparseMatrix.constant(-3, 1000));
        assertEquals(SparseMatrix.InvalidLengthException.class, exception.getCause().getClass());
    }

    @Test
    public void testIdentity() {
        SparseMatrix<Integer> matrixMap = SparseMatrix.identity(3, 0, 1);
        assertEquals("Indexes[row=0, col=0]1\n" +
                        "Indexes[row=1, col=1]1\n" +
                        "Indexes[row=2, col=2]1\n",
                matrixMap.toString());
    }

    @Test
    public void testIdentityNull() {
        String message = "Null found in identity() - SparseMatrix";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                SparseMatrix.identity(3,1, null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testIdentityOutOfBound() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                SparseMatrix.identity(-3,1, 1));
        assertEquals(SparseMatrix.InvalidLengthException.class, exception.getCause().getClass());
    }

    @Test
    public void testFrom() {
        Integer[][] matrix = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        assertEquals("Indexes[row=0, col=0]1\n"+
        "Indexes[row=1, col=1]1\n"+
        "Indexes[row=2, col=2]1\n",
                SparseMatrix.from(matrix).toString());
    }

    @Test
    public void testFromNull() {
        String message = "Null found in from() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                SparseMatrix.from(null));
    }

    @Test
    public void testPlusToSparseMatrix() {
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        SparseMatrix<Integer> matrix1 = SparseMatrix.from(list1);

        Integer[][] list2 = new Integer[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        SparseMatrix<Integer> matrix2 = SparseMatrix.from(list2);

        Integer[][] listResult = new Integer[][] {
                {1,0,0},
                {0,2,0},
                {0,0,2}
        };
        SparseMatrix<Integer> result = SparseMatrix.from(listResult);
        assertEquals(matrix1.plus(matrix2, Integer::sum).toString(), result.toString());
    }

    @Test
    public void testPlusToMatrixMap() {
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        MatrixMap<Integer> matrix1 = MatrixMap.from(list1);

        Integer[][] list2 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        SparseMatrix<Integer> matrix2 = SparseMatrix.from(list2);
        assertEquals("Indexes[row=0, col=0]2\n" +
                        "Indexes[row=1, col=1]2\n" +
                        "Indexes[row=2, col=2]2\n",
                matrix2.plus(matrix1, Integer::sum).toString());
    }

    @Test
    public void testPlusToNull() {
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        SparseMatrix<Integer> matrix1 = SparseMatrix.from(list1);
        String message = "Null found in plus - SparseMatrix";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                matrix1.plus(null, Integer::sum));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testTimesToSparseMatrix() {
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        SparseMatrix<Integer> matrix1 = SparseMatrix.from(list1);

        Integer[][] list2 = new Integer[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        SparseMatrix<Integer> matrix2 = SparseMatrix.from(list2);

        assertEquals("Indexes[row=0, col=0]0\n" +
                       "Indexes[row=1, col=1]1\n" +
//                        "Indexes[row=2, col=2]1\n" +
                        "Indexes[row=0, col=1]0\n" +
//                        "Indexes[row=1, col=2]0\n" +
//                        "Indexes[row=0, col=2]0\n" +
//                        "Indexes[row=2, col=0]0\n" +
                        "Indexes[row=1, col=0]0\n"
//                        "Indexes[row=2, col=1]0\n"
                ,
                matrix1.times(matrix2, new IntegerRing()).toString());
    }

    @Test
    public void testTimesToMatrixMap() {
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        MatrixMap<Integer> matrix1 = MatrixMap.from(list1);

        Integer[][] list2 = new Integer[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        SparseMatrix<Integer> matrix2 = SparseMatrix.from(list2);
        assertEquals("Indexes[row=0, col=0]0\n"
                        + "Indexes[row=1, col=1]1\n" +
//                        "Indexes[row=2, col=2]1\n"
                        "Indexes[row=0, col=1]0\n" +
//                        "Indexes[row=1, col=2]0\n" +
//                        "Indexes[row=0, col=2]0\n" +
//                        "Indexes[row=2, col=0]0\n" +
                        "Indexes[row=1, col=0]0\n"
//                        "Indexes[row=2, col=1]0\n"
                ,matrix2.times(matrix1, new IntegerRing()).toString());
    }

    @Test
    public void testTimesToNull() {
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        SparseMatrix<Integer> matrix1 = SparseMatrix.from(list1);
        String message = "Null found in times() - SparseMatrix";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                matrix1.times(null, new IntegerRing()));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testToMatrixMap() {
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        SparseMatrix<Integer> matrix1 = SparseMatrix.from(list1);
        Integer[][] list2 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        MatrixMap<Integer> matrix2 = MatrixMap.from(list2);
        assertEquals(matrix2.toString(), matrix1.toMatrixMap().toString());
    }

    @Test
    public void stressTestToSparseMatrix() {
        IntegerRing ring = new IntegerRing();
        int rows = 1000;
        int cols = 1000;

        SparseMatrix<Integer> matrixMap = SparseMatrix.instance(rows, cols, indexes -> indexes.row() + indexes.col());

        MatrixMap<Integer> sparseMatrix = matrixMap.toMatrixMap();

        assertNotNull(sparseMatrix);
    }
}

