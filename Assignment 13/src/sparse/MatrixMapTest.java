package sparse;

import Assignment2.IntegerRing;
import org.junit.Test;

import static org.junit.Assert.*;

/** Test for MatrixMap */
public class MatrixMapTest<Indexes, T> {
    @Test
    public void testSize() {
        Integer[][] matrix = new Integer[][]{
                {1, 1, 1, 6, 1},
                {2, 2, 2, 200, 0},
                {3, 3, 3, 82, 0},
                {4, 5, 6, 7, 0},
                {6, 2, 50, 4, 8, 0}
        };
        assertEquals(new sparse.Indexes(4, 4), MatrixMap.from(matrix).size());
    }

    @Test
    public void testValueIndexes() {
        Integer[][] matrix = new Integer[][]{
                {2, 1, 1},
                {2, -3, 4}
        };
        assertEquals(-3, MatrixMap.from(matrix).value(new sparse.Indexes(1, 1)).intValue());
    }

    @Test
    public void testValueIndexesNull() {
        String message = "Null found in value() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                MatrixMap.from(new Integer[1][1]).value(null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testValueRowAndCol() {
        Integer[][] matrix = new Integer[][]{
                {1, -2, -3},
                {3, 4, 0},
                {5, -6, 0}
        };
        assertEquals(1, MatrixMap.from(matrix).value(0, 0).intValue());
    }

    @Test
    public void testValueRowAndColNull() {
        String message = "Null found in value() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                MatrixMap.from(new Integer[2][2]).value(null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testInstanceRowsAndColumns() {
        MatrixMap<Integer> matrixMap = MatrixMap.instance(3, 2, indexes -> indexes.row() + indexes.col());
        assertEquals("Indexes[row=0, col=0]0\n" +
                        "Indexes[row=0, col=1]1\n" +
                        "Indexes[row=1, col=0]1\n" +
                        "Indexes[row=1, col=1]2\n" +
                        "Indexes[row=2, col=0]2\n" +
                        "Indexes[row=2, col=1]3\n",
                matrixMap.toString());
    }

    @Test
    public void testInstanceRowsAndColumnsNull() {
        String message = "Null found in instance() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                MatrixMap.instance(3,2, null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testInstanceRowsAndColumnsOutOfBound() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                MatrixMap.instance(-3,2, indexes -> indexes.row() + indexes.col()));
        assertEquals(MatrixMap.InvalidLengthException.class, exception.getCause().getClass());
    }

    @Test
    public void testInstanceSize() {
        MatrixMap<Integer> matrixMap = MatrixMap.instance(new sparse.Indexes(2,1), indexes -> indexes.row() + indexes.col());
        assertEquals("Indexes[row=0, col=0]0\n" +
                        "Indexes[row=0, col=1]1\n" +
                        "Indexes[row=1, col=0]1\n" +
                        "Indexes[row=1, col=1]2\n" +
                        "Indexes[row=2, col=0]2\n" +
                        "Indexes[row=2, col=1]3\n",
                matrixMap.toString());
    }

    @Test
    public void testInstanceSizeNull() {
        String message = "Null found in instance() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                MatrixMap.instance(new sparse.Indexes(3,2), null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testInstanceSizeOutOfBound() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                MatrixMap.instance(new sparse.Indexes(-3,2), indexes -> indexes.row() + indexes.col()));
        assertEquals(MatrixMap.InvalidLengthException.class, exception.getCause().getClass());
    }

    @Test
    public void testConstant() {
        MatrixMap<Integer> map1 = MatrixMap.constant(2, 10);
        assertEquals(
                "Indexes[row=0, col=0]10\n" +
                        "Indexes[row=0, col=1]10\n" +
                        "Indexes[row=1, col=0]10\n"+
                        "Indexes[row=1, col=1]10\n",
                map1.toString());

        MatrixMap<Integer> matrixMap2 = MatrixMap.constant(2, 2000);
        assertEquals("Indexes[row=0, col=0]2000\n" +
                        "Indexes[row=0, col=1]2000\n" +
                        "Indexes[row=1, col=0]2000\n" +
                        "Indexes[row=1, col=1]2000\n",
                matrixMap2.toString());
    }

    @Test
    public void testConstantNull() {
        String message = "Null found in constant() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                MatrixMap.constant(3, null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testConstantOutOfBound() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                MatrixMap.constant(-3, 1000));
        assertEquals(MatrixMap.InvalidLengthException.class, exception.getCause().getClass());
    }

    @Test
    public void testIdentity() {
        MatrixMap<Integer> matrixMap = MatrixMap.identity(3, 0, 1);
        assertEquals("Indexes[row=0, col=0]1\n" +
                        "Indexes[row=0, col=1]0\n" +
                        "Indexes[row=0, col=2]0\n" +
                        "Indexes[row=1, col=0]0\n" +
                        "Indexes[row=1, col=1]1\n" +
                        "Indexes[row=1, col=2]0\n" +
                        "Indexes[row=2, col=0]0\n" +
                        "Indexes[row=2, col=1]0\n" +
                        "Indexes[row=2, col=2]1\n",
                matrixMap.toString());
    }

    @Test
    public void testIdentityNull() {
        String message = "Null found in identity() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                MatrixMap.identity(3,1, null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testIdentityOutOfBound() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                MatrixMap.identity(-3,1, 1));
        assertEquals(MatrixMap.InvalidLengthException.class, exception.getCause().getClass());
    }

    @Test
    public void testFrom() {
        Integer[][] matrix = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        assertEquals("Indexes[row=0, col=0]1\n" +
                        "Indexes[row=0, col=1]0\n" +
                        "Indexes[row=0, col=2]0\n" +
                        "Indexes[row=1, col=0]0\n" +
                        "Indexes[row=1, col=1]1\n" +
                        "Indexes[row=1, col=2]0\n" +
                        "Indexes[row=2, col=0]0\n" +
                        "Indexes[row=2, col=1]0\n" +
                        "Indexes[row=2, col=2]1\n",
                MatrixMap.from(matrix).toString());
    }

    @Test
    public void testFromNull() {
        String message = "Null found in from() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                MatrixMap.from(null));
    }


    /** New Test from Assignment 12 */
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
        MatrixMap<Integer> matrix2 = MatrixMap.from(list2);
        assertEquals("Indexes[row=0, col=0]2\n" +
                        "Indexes[row=1, col=1]2\n" +
                        "Indexes[row=2, col=2]2\n" +
                        "Indexes[row=0, col=1]0\n" +
                        "Indexes[row=1, col=2]0\n" +
                        "Indexes[row=0, col=2]0\n" +
                        "Indexes[row=2, col=0]0\n" +
                        "Indexes[row=1, col=0]0\n" +
                        "Indexes[row=2, col=1]0\n",
                matrix1.plus(matrix2, Integer::sum).toString());
    }

    @Test
    public void testPlusToSparseMap() {
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
                        "Indexes[row=2, col=2]2\n" +
                        "Indexes[row=0, col=1]0\n" +
                        "Indexes[row=1, col=2]0\n" +
                        "Indexes[row=0, col=2]0\n" +
                        "Indexes[row=2, col=0]0\n" +
                        "Indexes[row=1, col=0]0\n" +
                        "Indexes[row=2, col=1]0\n",
                matrix1.plus(matrix2, Integer::sum).toString());
    }

    @Test
    public void testPlusToNull() {
        Integer[][] list1 = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        MatrixMap<Integer> matrix1 = MatrixMap.from(list1);
        String message = "Null found in plus - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                matrix1.plus(null, Integer::sum));
        assertEquals(message, exception.getMessage());
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
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        MatrixMap<Integer> matrix2 = MatrixMap.from(list2);
        assertEquals("Indexes[row=0, col=0]1\n" +
                        "Indexes[row=1, col=1]1\n" +
                        "Indexes[row=0, col=1]0\n" +
                        "Indexes[row=1, col=0]0\n"
                       ,
                matrix1.times(matrix2, new IntegerRing()).toString());
    }

    @Test
    public void testTimesToSparseMatrix() {
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
        assertEquals("Indexes[row=0, col=0]1\n"+
                        "Indexes[row=1, col=1]1\n" +
//                        "Indexes[row=2, col=2]1\n" +
                        "Indexes[row=0, col=1]0\n" +
//                        "Indexes[row=1, col=2]0\n" +
//                        "Indexes[row=0, col=2]0\n" +
//                        "Indexes[row=2, col=0]0\n" +
                        "Indexes[row=1, col=0]0\n"
//                        "Indexes[row=2, col=1]0\n",
                ,
                matrix1.times(matrix2, new IntegerRing()).toString());
    }

    @Test
    public void testToSparseMatrix() {
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

        assertEquals(matrix2.toString(), matrix1.toSparseMatrix().toString());
    }

    @Test
    public void stressTestToSparseMatrix() {
        IntegerRing ring = new IntegerRing();
        int rows = 1000;
        int cols = 1000;

        MatrixMap<Integer> matrixMap = MatrixMap.instance(rows, cols, indexes -> indexes.row() + indexes.col());

        SparseMatrix<Integer> sparseMatrix = matrixMap.toSparseMatrix();

        assertNotNull(sparseMatrix);
    }

    //Make sparseMatrixRing class
}

