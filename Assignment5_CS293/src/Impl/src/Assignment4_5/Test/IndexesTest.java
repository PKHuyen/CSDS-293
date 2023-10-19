package Assignment4_5.Test;

import Assignment4_5.Indexes;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IndexesTest {
    private Indexes createIndexes(int row, int col) {
        return new Indexes(row, col);
    }
    @Test
    void testCompareToSameRowSameCol() {
        Indexes thisIndexes = createIndexes(3,3);
        Indexes otherIndexes = createIndexes(3,3);

        assertEquals(0, thisIndexes.compareTo(otherIndexes));
    }

    @Test
    void testCompareToSameRowDifferentCol() {
        Indexes thisIndexes = createIndexes(3,3);
        Indexes otherIndexes = createIndexes(3,2);

        assertEquals(1, thisIndexes.compareTo(otherIndexes));
    }

    @Test
    void testCompareToDifferentRowSameCol() {
        Indexes thisIndexes = createIndexes(2,3);
        Indexes otherIndexes = createIndexes(3,3);

        assertEquals(-1, thisIndexes.compareTo(otherIndexes));
    }

    @Test
    void testCompareToError() {
        String message = "Null found on compareTo() - Indexes";
        Indexes thisIndexes = createIndexes(2,3);
        Throwable exception = assertThrows(NullPointerException.class, () -> thisIndexes.compareTo(null));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testAreDiagonalAreTrue() {
        assertTrue(new Indexes(1,1).areDiagonal());
    }

    @Test
    void testAreDiagonalAreFalse() {
        assertFalse(new Indexes(2,1).areDiagonal());
    }

    @Test
    void testStreamFromTo() {
        List<Indexes> list1 = Indexes.stream(new Indexes(0, 1), new Indexes(1, 3)).toList();
        List<Indexes> expectedList1 = Arrays.asList(new Indexes[]{
                new Indexes(0, 1),
                new Indexes(0, 2),
                new Indexes(0, 3),
                new Indexes(1, 1),
                new Indexes(1, 2),
                new Indexes(1, 3)});
        assertEquals(expectedList1.toString(), list1.toString());
    }

    @Test
    void testStreamFromToNullIndexes() {
        String message = "Null found on stream() - Indexes";
        Throwable exception = assertThrows(NullPointerException.class, () -> Indexes.stream(null, new Indexes(1, 3)).toList());
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testStreamSize() {
        List<Indexes> list1 = Indexes.stream(new Indexes(0, 1)).toList();
        List<Indexes> expectedList1 = Arrays.asList(new Indexes[]{
                new Indexes(0, 0),
                new Indexes(0, 1)});

        assertEquals(expectedList1.toString(), list1.toString());
    }

    @Test
    void testStreamSizeNull() {
        String message = "Null found on stream() - Indexes";
        Throwable exception = assertThrows(NullPointerException.class, () -> Indexes.stream(null, new Indexes(1, 3)).toList());
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testValue() {
        Indexes indexes = new Indexes(2, 3);
        Integer[][] matrix = new Integer[][]{
                {1, 2, 5, 4, 6},
                {4, 1, 6, 12, 500},
                {2, 3, 1000, 62, 100}
        };
        assertEquals(62, indexes.value(matrix).intValue());
    }
}
