package Assignment4_5;

import Assignment2.Ring;
import roamingcollection.RoamingBarricade;
import roamingcollection.RoamingMap;

import java.util.Collection;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class MatrixMap<T> {
    private RoamingBarricade<Indexes, T> matrix;

    /** Constructor MatrixMap */
    private MatrixMap(RoamingBarricade<Indexes, T> matrix) {
        this.matrix = matrix;
    }

    /** Return the size of Matrix*/
    public Indexes size() {
        Objects.requireNonNull(matrix);
        Indexes firstIndex = matrix.keySet().iterator().next();
        return new Indexes(firstIndex.row() + 1, firstIndex.col() + 1);
    }

    /** Override toString() to print the matrixMap */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Indexes, T> entry : matrix.entrySet()) {
            result.append(entry.getKey()).append(entry.getValue()).append("\n");
        }
        return result.toString();
    }

    /** Retrieve the value from the Indexes of the MatrixMap */
    public T value(Indexes indexes) {
        Objects.requireNonNull(indexes, "Null found in value() - MatrixMap");
        return matrix.get(indexes);
    }

    /** Retrieve the value from the row and col of the MatrixMap */
    public T value(int row, int col) {
        Objects.requireNonNull(row, "Null found in value() - MatrixMap");
        Objects.requireNonNull(col, "Null found in value() - MatrixMap");
        return value(new Indexes(row , col));
    }

    /** Instantiate a MatrixMap */
    public static <S> MatrixMap<S> instance(int rows, int columns, Function<Indexes, S> valueMapper) {
        Objects.requireNonNull(rows, "Null found in instance() - MatrixMap");
        Objects.requireNonNull(columns, "Null found in instance() - MatrixMap");
        Objects.requireNonNull(valueMapper, "Null found in instance() - MatrixMap");

        InvalidLengthException.requireNonEmpty(InvalidLengthException.Cause.ROW, rows);
        InvalidLengthException.requireNonEmpty(InvalidLengthException.Cause.COLUMN, columns);

        RoamingBarricade<Indexes, S> matrix = IntStream.range(0, rows)
                .boxed()
                .flatMap(i -> IntStream.range(0, columns)
                        .mapToObj(j -> new Indexes(i, j)))
                .collect(Collectors.toMap(
                        Function.identity(),
                        valueMapper,
                        (e1, e2) -> { assert false; return null;},
                        RoamingBarricade::new)
                );

        return new MatrixMap<>(matrix);
    }

    /** Instantiate a MatrixMap */
    public static <S> MatrixMap<S> instance(Indexes size, Function<Indexes, S> valueMapper) {
        Objects.requireNonNull(size, "Null found in instance() - MatrixMap");
        Objects.requireNonNull(valueMapper, "Null found in instance() - MatrixMap");

        return instance(size.row() + 1, size.col() + 1, valueMapper);
    }

    /** Instantiate a MatrixMap */
    public static <S> MatrixMap<S> constant(int size, S value) {
        Objects.requireNonNull(size, "Null found in constant() - MatrixMap");
        Objects.requireNonNull(value, "Null found in constant() - MatrixMap");

        return instance(size, size, indexes -> value);
    }

    /** Get the identity to MatrixMap */
    public static <S> MatrixMap<S> identity(int size, S zero, S identity) {
        Objects.requireNonNull(size, "Null found in identity() - MatrixMap");
        Objects.requireNonNull(zero, "Null found in identity() - MatrixMap");
        Objects.requireNonNull(identity, "Null found in identity() - MatrixMap");

        return instance(size, size, indexes -> indexes.areDiagonal() ? identity : zero);
    }

    /** Return the MatrixMap with matrix */
    public static <S> MatrixMap<S> from(S[][] matrix) {
        int row = matrix.length;
        int col = matrix.length;
        Objects.requireNonNull("Null found in from() - MatrixMap");

        return instance(row, col, indexes -> matrix[indexes.row()][indexes.col()]);
    }


    public MatrixMap<T> plus (MatrixMap<T> other, BinaryOperator<T> plus) {
        Objects.requireNonNull(other, "Null found in plus - MatrixMap");
        Objects.requireNonNull(plus, "Null found in plus - MatrixMap");
        InconsistentSizeException.requireMatchingSize(this, other);

        RoamingBarricade<Indexes, T> result = matrix.entrySet().
                stream().collect(Collectors.toMap(entry -> entry.getKey(),
                        entry -> plus.apply(entry.getValue(), other.matrix.get(entry.getKey())),
                        (e1, e2) -> { assert false; return null; },
                        RoamingBarricade::new)
                );

        return new MatrixMap<>(result);
    }

    public MatrixMap<T> times(MatrixMap<T> other, Ring<T> ring) {
        Objects.requireNonNull(other, "Null found in times() - MatrixMap");
//        Objects.requireNonNull(ring, "Null found in times() - MatrixMap");

        NonSquareException.requireDiagonal(other.size());
        NonSquareException.requireDiagonal(this.size());
        InconsistentSizeException.requireMatchingSize(this, other);

        RoamingBarricade<Indexes, T> resultMap = new RoamingBarricade<>();
        Indexes.stream(size().row() - 1, size().col() - 1).
                forEach(index -> {
                    T result = ring.zero();
                    for (int i = 0; i < this.size().row(); i++) {
                        result = ring.sum(result, ring.product(this.value(index.row(), i), other.value(i, index.col())));
                    }
                    resultMap.put(new Indexes(index.row(), index.col()), result);
                });
        return new MatrixMap<>(resultMap);
    }

    /** InvalidLengthException */
    public static class InvalidLengthException extends Exception {
        private final long serialVersionUID = 0;
        private final Cause cause;
        private final int length;

        public enum Cause { ROW, COLUMN }

        /** Constructor */
        public InvalidLengthException(Cause cause, int length) {
            this.cause = cause;
            this.length = length;
        }

        /** Retrieve Cause message*/
        public Cause getTheCause() {
         return this.cause;
        }

        /** Retrieve length */
        public int getLength() {
            return this.length;
        }

        /** Create new InvalidLengthException*/
        public static int requireNonEmpty(Cause cause, int length) {
            if (checkNonEmpty(length)) throw new IllegalArgumentException(new InvalidLengthException(cause, length));
            return length;
        }

        /** Helper method */
        private static boolean checkNonEmpty(int val) {
            return (val <= 0) ? true : false;
        }
    }

    public static class InconsistentSizeException extends Exception{
        private  Indexes thisIndexes;
        private  Indexes otherIndexes;

        public InconsistentSizeException(Indexes thisIndexes, Indexes otherIndexes) {
            this.thisIndexes = thisIndexes;
            this.otherIndexes = otherIndexes;
        }

        public Indexes getThisIndexes() {
            return thisIndexes;
        }

        public Indexes getOtherIndexes() {
            return otherIndexes;
        }

        public static <T> Indexes requireMatchingSize(MatrixMap<T> thisMatrix, MatrixMap<T> otherMatrix) {
            Objects.requireNonNull(thisMatrix, "Null found in requireMatchingSize");
            Objects.requireNonNull(otherMatrix, "Null found in requireMatchingSize");
            if (thisMatrix.size().compareTo(otherMatrix.size()) == 0)
                return new Indexes(thisMatrix.size().row(), thisMatrix.size().col());
            throw new IllegalArgumentException("Size not match");
        }
    }

    public static class NonSquareException extends IllegalStateException {
        private final Indexes indexes;

        public NonSquareException(Indexes indexes) {
            this.indexes = indexes;
        }

        public Indexes getIndexes() {
            return indexes;
        }

        public static Indexes requireDiagonal(Indexes indexes) {
            if (indexes.areDiagonal())
                return new Indexes(indexes.row(), indexes.col());
            throw new IllegalStateException("Size not match");
        }
    }
}
