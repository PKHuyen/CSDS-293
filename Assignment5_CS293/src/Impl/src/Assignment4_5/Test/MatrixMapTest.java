package Assignment4_5.Test;

import Assignment4_5.MatrixMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixMapTest<Indexes, T> {
    @Test
    void testSize() {
        Integer[][] matrix = new Integer[][]{
                {1, 1, 1, 6, 1},
                {2, 2, 2, 200, 0},
                {3, 3, 3, 82, 0},
                {4, 5, 6, 7, 0},
                {6, 2, 50, 4, 8, 0}
        };
        assertTrue(new Assignment4_5.Indexes(1, 1).compareTo(MatrixMap.from(matrix).size()) == 0);
    }

    @Test
    void testValueIndexes() {
        Integer[][] matrix = new Integer[][]{
                {2, 1, 1},
                {2, -3, 4}
        };
        assertEquals(-3, MatrixMap.from(matrix).value(new Assignment4_5.Indexes(1, 1)).intValue());
    }

    @Test
    void testValueIndexesNull() {
        String message = "Null found in value() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                MatrixMap.from(new Integer[1][1]).value(null));
        assertEquals(null, exception.getMessage());
    }

    @Test
    void testValueRowAndCol() {
        Integer[][] matrix = new Integer[][]{
                {1, -2, -3},
                {3, 4, 0},
                {5, -6, 0}
        };
        assertEquals(1, MatrixMap.from(matrix).value(0, 0).intValue());
    }

    @Test
    void testValueRowAndColNull() {
        String message = "Null found in value() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                MatrixMap.from(new Integer[3][2]).value(null));
        assertEquals(null, exception.getMessage());
    }

    @Test
    void testInstanceRowsAndColumns() {
        MatrixMap<Integer> matrixMap = MatrixMap.instance(3, 2, indexes -> indexes.row() + indexes.col());
        assertEquals("Indexes[row=0, col=0]0\n" +
                "Indexes[row=1, col=1]2\n" +
                "Indexes[row=0, col=1]1\n" +
                "Indexes[row=2, col=0]2\n" +
                "Indexes[row=1, col=0]1\n" +
                "Indexes[row=2, col=1]3\n",
                matrixMap.toString());
    }

    @Test
    void testInstanceRowsAndColumnsNull() {
        String message = "Null found in instance() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                MatrixMap.instance(3,2, null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testInstanceRowsAndColumnsOutOfBound() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                MatrixMap.instance(-3,2, indexes -> indexes.row() + indexes.col()));
        assertEquals(MatrixMap.InvalidLengthException.class, exception.getCause().getClass());
    }

    @Test
    void testInstanceSize() {
        MatrixMap<Integer> matrixMap = MatrixMap.instance(new Assignment4_5.Indexes(2,1), indexes -> indexes.row() + indexes.col());
        assertEquals("Indexes[row=0, col=0]0\n" +
                        "Indexes[row=1, col=1]2\n" +
                        "Indexes[row=0, col=1]1\n" +
                        "Indexes[row=2, col=0]2\n" +
                        "Indexes[row=1, col=0]1\n" +
                        "Indexes[row=2, col=1]3\n",
                matrixMap.toString());
    }

    @Test
    void testInstanceSizeNull() {
        String message = "Null found in instance() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                MatrixMap.instance(new Assignment4_5.Indexes(3,2), null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testInstanceSizeOutOfBound() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                MatrixMap.instance(new Assignment4_5.Indexes(-3,2), indexes -> indexes.row() + indexes.col()));
        assertEquals(MatrixMap.InvalidLengthException.class, exception.getCause().getClass());
    }

    @Test
    void testConstant() {
        MatrixMap<Integer> map1 = MatrixMap.constant(2, 10);
        assertEquals(
                "Indexes[row=0, col=0]10\n" +
                "Indexes[row=1, col=1]10\n" +
                "Indexes[row=0, col=1]10\n" +
                "Indexes[row=1, col=0]10\n",
                map1.toString());

        MatrixMap<Integer> matrixMap2 = MatrixMap.constant(2, 2000);
        assertEquals("Indexes[row=0, col=0]2000\n" +
                "Indexes[row=1, col=1]2000\n" +
                "Indexes[row=0, col=1]2000\n" +
                "Indexes[row=1, col=0]2000\n",
                matrixMap2.toString());
    }

    @Test
    void testConstantNull() {
        String message = "Null found in constant() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                MatrixMap.constant(3, null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testConstantOutOfBound() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                MatrixMap.constant(-3, 1000));
        assertEquals(MatrixMap.InvalidLengthException.class, exception.getCause().getClass());
    }

    @Test
    void testIdentity() {
        MatrixMap<Integer> matrixMap = MatrixMap.identity(3, 0, 1);
        System.out.println(matrixMap);
        assertEquals("Indexes[row=0, col=0]1\n" +
                "Indexes[row=1, col=1]1\n" +
                "Indexes[row=2, col=2]1\n" +
                "Indexes[row=0, col=1]0\n" +
                "Indexes[row=1, col=2]0\n" +
                "Indexes[row=0, col=2]0\n" +
                "Indexes[row=2, col=0]0\n" +
                "Indexes[row=1, col=0]0\n" +
                "Indexes[row=2, col=1]0\n",
                matrixMap.toString());
    }

    @Test
    void testIdentityNull() {
        String message = "Null found in identity() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                MatrixMap.identity(3,1, null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testIdentityOutOfBound() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                MatrixMap.identity(-3,1, 1));
        assertEquals(MatrixMap.InvalidLengthException.class, exception.getCause().getClass());
    }

    @Test
    void testFrom() {
        Integer[][] matrix = new Integer[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        assertEquals("Indexes[row=0, col=0]1\n" +
                "Indexes[row=1, col=1]1\n" +
                "Indexes[row=2, col=2]1\n" +
                "Indexes[row=0, col=1]0\n" +
                "Indexes[row=1, col=2]0\n" +
                "Indexes[row=0, col=2]0\n" +
                "Indexes[row=2, col=0]0\n" +
                "Indexes[row=1, col=0]0\n" +
                "Indexes[row=2, col=1]0\n",
                MatrixMap.from(matrix).toString());
    }

    @Test
    void testFromNull() {
        String message = "Null found in from() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                MatrixMap.from(null));
    }
}


