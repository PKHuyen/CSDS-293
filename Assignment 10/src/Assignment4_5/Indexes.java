package Assignment4_5;

import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Indexes(int row, int col) implements Comparable<Indexes> {

    /** Override the compareTo to check if values are the same*/
    @Override
    public int compareTo(Indexes other) {
        Objects.requireNonNull(other, "Null found on compareTo() - Indexes");
        return (this.row != other.row) ? Integer.compare(this.row, other.row) : Integer.compare(this.col, other.col);
    }

    /** Check if the index is in diagonal */
    public boolean areDiagonal() {
        return this.row == this.col;
    }

    /** Main function: Stream */
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

    /** Main function: Stream */
    public static Stream<Indexes> stream(Indexes size) {
        Objects.requireNonNull(size);
        if (checkLengthOutOfBound(size.row(), size.col())) throw new IllegalArgumentException("Out of bound argument");
        return stream(new Indexes(0, 0), size);
    }

    /** Main function: Stream */
    public static Stream<Indexes> stream(int rows, int columns) {
        Objects.requireNonNull(rows);
        Objects.requireNonNull(columns);
        if (checkLengthOutOfBound(rows, columns)) throw new IllegalArgumentException("Out of bound argument");
        return stream(new Indexes(0, 0), new Indexes(rows, columns));
    }

    /** Main function: value - to retrieve value from matrix */
    public <S> S value(S[][] matrix) {
        Objects.requireNonNull(matrix,"Null found on value() - Indexes");
        return matrix[row()][col()];
    }

    public <S> S value(MatrixMap<S> matrix){
        return matrix.value(this);
    }

    private static boolean checkLengthOutOfBound(int row, int col) {
        return (row < 0 && col < 0) ? true : false;
    }

    private boolean checkRow(Indexes from, Indexes to) {
        assert from != null;
        assert to != null;
        return (from.row > to.row) ? false : true;
    }

    private boolean checkCol(Indexes from, Indexes to) {
        assert from != null;
        assert to != null;
        return ((from.row == to.row) && (from.col > to.col)) ? false : true;
    }
}
