/**
 * @author Harley Phung
 * Email hkp15
 */
package sparse;

import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Demonstation of Indexes in Matrix. Implements Comparable
 * */
public record Indexes(int row, int col) implements Comparable<Indexes> {

    /**
     * Override the compareTo to check if values are the same
     * @param other compareTo the other Indexes
     * @return how this index is compare to other index
     * @throws NullPointerException if parameter is null
     * */
    @Override
    public int compareTo(Indexes other) {
        Objects.requireNonNull(other, "Null found on compareTo() - Indexes");
        return (this.row != other.row) ? Integer.compare(this.row, other.row) : Integer.compare(this.col, other.col);
    }

    /**
     * Check if the index is in diagonal
     * @return boolean if the indices are diagonal
     * */
    public boolean areDiagonal() {
        return this.row == this.col;
    }

    /**
     * Stream to go through the Indexes
     * @param from start index
     * @param to end index
     * @return stream of the new indexes
     * @throws NullPointerException if parameters are null
     * */
    public static Stream<Indexes> stream(Indexes from, Indexes to) {
        Objects.requireNonNull(from, "Null found on stream() - Indexes");
        Objects.requireNonNull(to, "Null found on stream() - Indexes");

        return IntStream.rangeClosed(from.row(), to.row())
                .boxed()
                .flatMap(row ->
                        IntStream.rangeClosed(from.col(), to.col())
                                .mapToObj(column -> new Indexes(row, column))
                );
    }

    /**
     * Stream to go through the Indexes
     * @param size the size of the indexes
     * @return stream of the new indexes
     * @throws NullPointerException if parameters are null
     * */
    public static Stream<Indexes> stream(Indexes size) {
        Objects.requireNonNull(size);
        if (checkLengthOutOfBound(size.row(), size.col())) throw new IllegalArgumentException("Out of bound argument");
        return stream(new Indexes(0, 0), size);
    }

    /**
     * Stream to go through the Indexes
     * @param rows the number of rows in indexes
     * @param columns the number of columns in indexes
     * @return stream of the new indexes
     * @throws NullPointerException if parameters are null
     * */
    public static Stream<Indexes> stream(int rows, int columns) {
        Objects.requireNonNull(rows);
        Objects.requireNonNull(columns);
        if (checkLengthOutOfBound(rows, columns)) throw new IllegalArgumentException("Out of bound argument");
        return stream(new Indexes(0, 0), new Indexes(rows, columns));
    }

    /**
     * Value of the indexes from 2-dimensional matrix
     * @param matrix 2-dimensional matrix
     * @param <S> Type of the indexes
     * @return value at the index
     * @throws NullPointerException if parameter is null
     * */
    public <S> S value(S[][] matrix) {
        Objects.requireNonNull(matrix,"Null found on value() - Indexes");
        return matrix[row()][col()];
    }

    /**
     * Value of the indexes from 2-dimensional matrix
     * @param matrix MatrixMap
     * @param <S> Type of the indexes
     * @return value of the matrix
     * @throws NullPointerException if parameter is null
     * */
    public <S> S value(MatrixMap<S> matrix){
        return matrix.value(this);
    }

    /**
     * Check if the index is out of bound
     * @param row row of the checking index
     * @param col column of the checking index
     * @return if the index is out of bound
     * */
    private static boolean checkLengthOutOfBound(int row, int col) {
        return (row < 0 && col < 0) ? true : false;
    }

    /**
     * Check if the indexes range in row is out of bound
     * @param from starting indexes
     * @param to ending indexes
     * @return if the indexes range is out of bound
     * @throws NullPointerException if the parameters is null
     * */
    private boolean checkRow(Indexes from, Indexes to) {
        assert from != null;
        assert to != null;
        return (from.row > to.row) ? false : true;
    }

    /**
     * Check if the indexes range in column is out of bound
     * @param from starting indexes
     * @param to ending indexes
     * @return if the indexes range is out of bound
     * @throws NullPointerException if the parameters is null
     * */
    private boolean checkCol(Indexes from, Indexes to) {
        assert from != null;
        assert to != null;
        return ((from.row == to.row) && (from.col > to.col)) ? false : true;
    }
}
