package Assignment4_5;
import org.junit.Before;
import org.junit.Test;
import roamingcollection.RoamingBarricade;
import roamingcollection.RoamingMap;

import java.util.Map;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class MatrixMapTest<Indexes, T> {
    private final Logger logger = Logger.getLogger(RoamingBarricade.class.getName());
    private final LoggerTestingHandler handler = new LoggerTestingHandler();

    @Before
    public void setup() {
        logger.addHandler(handler);
    }
    @Test
    public void testInvalidLengthException() {
        try {
            throw new MatrixMap.InvalidLengthException(MatrixMap.InvalidLengthException.Cause.ROW, 10);
        } catch (MatrixMap.InvalidLengthException e) {
            assertEquals(MatrixMap.InvalidLengthException.Cause.ROW, e.getTheCause());
            assertEquals(10, e.getLength());
        }

        try {
            throw new MatrixMap.InvalidLengthException(MatrixMap.InvalidLengthException.Cause.COLUMN, 5);
        } catch (MatrixMap.InvalidLengthException e) {
            assertEquals(MatrixMap.InvalidLengthException.Cause.COLUMN, e.getTheCause());
            assertEquals(5, e.getLength());
        }
    }
    @Test
    public void testSize() {
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
    public void testValueIndexes() {
        Integer[][] matrix = new Integer[][]{
                {2, 1, 1},
                {2, -3, 4}
        };
        assertEquals(null, MatrixMap.from(matrix).value(new Assignment4_5.Indexes(1, 1)));
    }

    @Test
    public void testValueIndexesNull() {
        String message = "Null found in value() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                MatrixMap.from(new Integer[1][1]).value(null));
        assertEquals(null, exception.getMessage());
    }

    @Test
    public void testValueRowAndCol() {
        Integer[][] matrix = new Integer[][]{
                {1, -2, -3},
                {3, 4, 0},
                {5, -6, 0}
        };
        assertEquals(null, MatrixMap.from(matrix).value(1, 1)); //RoamingMap so lots of function return null
    }

    @Test
    public void testValueRowAndColNull() {
        String message = "Null found in value() - MatrixMap";
        Throwable exception = assertThrows(NullPointerException.class, () ->
                MatrixMap.from(new Integer[3][2]).value(null));
        assertEquals(null, exception.getMessage());
    }

    @Test
    public void testInstanceRowsAndColumns() {
        MatrixMap<Integer> matrixMap = MatrixMap.instance(3, 2, indexes -> indexes.row() + indexes.col());
        assertEquals("Indexes[row=0, col=0]0\n" +
                "Indexes[row=0, col=1]1\n"+
        "Indexes[row=1, col=0]1\n"+
        "Indexes[row=1, col=1]2\n"+
        "Indexes[row=2, col=0]2\n"+
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
        MatrixMap<Integer> matrixMap = MatrixMap.instance(new Assignment4_5.Indexes(2,1), indexes -> indexes.row() + indexes.col());
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
                MatrixMap.instance(new Assignment4_5.Indexes(3,2), null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testInstanceSizeOutOfBound() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                MatrixMap.instance(new Assignment4_5.Indexes(-3,2), indexes -> indexes.row() + indexes.col()));
        assertEquals(MatrixMap.InvalidLengthException.class, exception.getCause().getClass());
    }

    @Test
    public void testConstant() {
        MatrixMap<Integer> map1 = MatrixMap.constant(2, 10);
        assertEquals(
                "Indexes[row=0, col=0]10\n" +
                        "Indexes[row=0, col=1]10\n" +
                        "Indexes[row=1, col=0]10\n" +
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
        System.out.println(matrixMap);
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

//    @Test
//    public void testPlusSquareMatrix() {
//        BigIntegerRing ring = new BigIntegerRing();
//        BigInteger[][] list1 = {{new BigInteger("1"), new BigInteger("2")},
//                {new BigInteger("3"), new BigInteger("4")}};
//
//        BigInteger[][] result = {{new BigInteger("2"), new BigInteger("4")},
//                {new BigInteger("6"), new BigInteger("8")}};
//
//
//        assertEquals(MatrixMap.from(result).toString(),
//                MatrixMap.from(list1).plus(MatrixMap.from(list1), (x, y) -> ring.sum(x,y)).toString());
//    }
//
//    @Test
//    public void testPlusNonSquareMatrix() {
//        BigInteger[][] list1 = {{new BigInteger("1"), new BigInteger("2"), new BigInteger("3")},
//                {new BigInteger("4"), new BigInteger("5"), new BigInteger("6")}};
//        BigInteger[][] result = {{new BigInteger("2"), new BigInteger("4"), new BigInteger("6")},
//                {new BigInteger("8"), new BigInteger("10"), new BigInteger("12")}};
//        BigIntegerRing ring = new BigIntegerRing();
//        assertEquals(MatrixMap.from(result).toString(),
//                MatrixMap.from(list1).plus(MatrixMap.from(list1), (x, y) -> ring.sum(x,y)).toString());
//    }
//
//    @Test
//    public void testPlusNull() {
//        BigIntegerRing ring = new BigIntegerRing();
//        BigInteger[][] list1 = {{new BigInteger("1"), new BigInteger("2"), new BigInteger("3")},
//                {new BigInteger("4"), new BigInteger("5"), new BigInteger("6")}};
//        String message = "Null found in plus - MatrixMap";
//        Throwable exception = assertThrows(NullPointerException.class, ()
//                -> MatrixMap.from(list1).plus(null, (x, y) -> ring.sum(x,y)).toString());
//        assertEquals(message, exception.getMessage());
//    }
//
//    @Test
//    public void testMultiplySquareMatrix() {
//        BigIntegerRing ring = new BigIntegerRing();
//        BigInteger[][] list1 = {{new BigInteger("1"), new BigInteger("2")},
//                {new BigInteger("3"), new BigInteger("4")}};
//
//        BigInteger[][] result = {{new BigInteger("1")}};
//
//        assertEquals(MatrixMap.from(result).toString(),
//                MatrixMap.from(list1).times(MatrixMap.from(list1), ring).toString());
//    }

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

    @Test
    public void testInconsistency() {
        handler.clearLogRecords();
        RoamingBarricade<Integer, Integer> barricade = new RoamingBarricade<>();
        Logger logger = Logger.getLogger(RoamingBarricade.class.getName());
        assertEquals(null, barricade.put(1,1));
        assertEquals(null, barricade.put(1,2));
        assertEquals(null, barricade.get(1));
        assertEquals("Inconsistency found", handler.getLastLog().orElse(""));

    }

    //Name change to specify Class
    @Test
    public void testGetOutOfBound() {
        Integer[][] matrix = new Integer[][]{
                {1, -2},
                {3, 4}
        };
        RoamingMap<Indexes, Integer> map = new RoamingMap<>(MatrixMap.from(matrix));
        assertTrue(3 == map.get(new Assignment4_5.Indexes(3,3)));
    }

    @Test
    public void testPut(){
        Integer[][] matrix = new Integer[][]{
                {1, -2},
                {3, 4}
        };
        RoamingMap<Assignment4_5.Indexes, Integer> map = new RoamingMap<>(MatrixMap.from(matrix));
        assertTrue(1 == map.put(new Assignment4_5.Indexes(1,1),2));
    }
}


