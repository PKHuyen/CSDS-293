/**
 * @author Harley Phung
 * Email hkp15
 */
package sparse;

import Assignment2.Ring;
import sparse.Indexes;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Demonstration of how Square MatrixMap can create a ring
 * @param <T> Type of the MatrixMapRing
 * */
public final class MatrixMap<T> implements Matrix<T>{
    /** Sparse Matrix is a type of Map */
    private final Map<Indexes, T> matrix;

    /**
     * Constructor
     * @param matrix map of the MatrixMap
     * */
    private MatrixMap(Map<Indexes, T> matrix) {
        this.matrix = matrix;
    }

    /**
     * Return the MatrixMap
     * @return map of the MatrixMap
     * */
    @Override
    public Map<Indexes, T> matrix() {
        return this.matrix;
    }

    /**
     * Return size of the MatrixMap
     * @return Indexes size of the MatrixMap
     * @throws NullPointerException if MatrixMap if null
     * */
    public Indexes size() {
        Objects.requireNonNull(matrix);
        Iterator<Indexes> keyIter = matrix.keySet().iterator();
        Indexes firstIndex = keyIter.next();
        while (keyIter.hasNext()) {
            firstIndex = keyIter.next();
        }
        return new Indexes(firstIndex.row(), firstIndex.col());
    }

    /**
     * MatrixMap in String format
     * @return string format of the MatrixMap
     * */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Indexes, T> entry : matrix.entrySet()) {
            result.append(entry.getKey()).append(entry.getValue()).append("\n");
        }
        return result.toString();
    }

    /**
     * Value at indexes
     * @param indexes Indexes of the MatrixMap
     * @return value of at the index entry
     * @throws NullPointerException if the index is out of bound or null
     * */
    public T value(Indexes indexes) {
        Objects.requireNonNull(indexes, "Null found in value() - MatrixMap");
        return matrix.get(indexes);
    }

    /**
     * Value at entry of matrix
     * @param row row of the entry
     * @param col column of the entry
     * @return value at the entry (row, col)
     * @throws NullPointerException if parameters are null
     * */
    public T value(int row, int col) {
        Objects.requireNonNull(row, "Null found in value() - MatrixMap");
        Objects.requireNonNull(col, "Null found in value() - MatrixMap");
        return value(new Indexes(row , col));
    }

    /**
     * Instantiate a MatrixMap
     * @param rows number of rows in total of MatrixMap
     * @param columns number of columns in total of MatrixMap
     * @param valueMapper function of the MatrixMap
     * @param <S> Type of the MatrixMap
     * @return new MatrixMap that have dimension rows x columns
     * @throws NullPointerException if parameters are null
     * @throws MatrixMap.InvalidLengthException if MatrixMap has invalid length
     * */
    public static <S> MatrixMap<S> instance(int rows, int columns, Function<Indexes, S> valueMapper) {
        Objects.requireNonNull(rows, "Null found in instance() - MatrixMap");
        Objects.requireNonNull(columns, "Null found in instance() - MatrixMap");
        Objects.requireNonNull(valueMapper, "Null found in instance() - MatrixMap");

        InvalidLengthException.requireNonEmpty(InvalidLengthException.Cause.ROW, rows);
        InvalidLengthException.requireNonEmpty(InvalidLengthException.Cause.COLUMN, columns);

        Map<Indexes, S> resultMatrix = new LinkedHashMap<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Indexes index = new Indexes(i, j);
                S value = valueMapper.apply(index);
                resultMatrix.put(index, value);
            }
        }
        return new MatrixMap<>(resultMatrix);
    }

    /**
     * Instantiate a MatrixMap
     * @param size indexes of the MatrixMap (dimension)
     * @param valueMapper function of the MatrixMap
     * @param <S> Type of the MatrixMap
     * @return new MatrixMap that have dimension rows x columns
     * @throws NullPointerException if parameters are null
     * */
    public static <S> MatrixMap<S> instance(Indexes size, Function<Indexes, S> valueMapper) {
        Objects.requireNonNull(size, "Null found in instance() - MatrixMap");
        Objects.requireNonNull(valueMapper, "Null found in instance() - MatrixMap");

        return instance(size.row() + 1, size.col() + 1, valueMapper);
    }

    /**
     * Instantiate a square MatrixMap
     * @param size indexes of the MatrixMap (dimension)
     * @param value value in each entry of the MatrixMap
     * @param <S> Type of the MatrixMap
     * @return new MatrixMap that have dimension size x size
     * @throws NullPointerException if parameters are null
     * */
    public static <S> MatrixMap<S> constant(int size, S value) {
        Objects.requireNonNull(size, "Null found in constant() - MatrixMap");
        Objects.requireNonNull(value, "Null found in constant() - MatrixMap");

        return instance(size, size, indexes -> value);
    }

    /**
     * Instantiate an identity MatrixMap
     * @param size indexes of the MatrixMap (dimension)
     * @param zero value of the zero entry
     * @param identity value of the non-zero entry
     * @param <S> Type of the MatrixMap
     * @return new MatrixMap that have dimension size x size
     * @throws NullPointerException if parameters are null
     * */
    public static <S> MatrixMap<S> identity(int size, S zero, S identity) {
        Objects.requireNonNull(size, "Null found in identity() - MatrixMap");
        Objects.requireNonNull(zero, "Null found in identity() - MatrixMap");
        Objects.requireNonNull(identity, "Null found in identity() - MatrixMap");

        return instance(size, size, indexes -> indexes.areDiagonal() ? identity : zero);
    }

    /**
     * Instantiate a MatrixMap from array
     * @param matrix 2-dimensional array of the MatrixMap
     * @param <S> Type of the MatrixMap
     * @return new MatrixMap that have entries being value from the matrix
     * */
    public static <S> MatrixMap<S> from(S[][] matrix) {
        int row = matrix.length;
        int col = matrix.length;
        Objects.requireNonNull("Null found in from() - MatrixMap");

        return instance(row, col, indexes -> matrix[indexes.row()][indexes.col()]);
    }

    /** Sum of 2 matrices. Matrix can be SparseMatrix or MatrixMap
     * @param other other Matrix, can be SparseMatrix or MatrixMap
     * @param plus Operator to do summation between 2 matrices
     * @return new MatrixMap of summation of 2 Matrices
     * @throws NullPointerException if parameters are null
     * */
    @Override
    public MatrixMap<T> plus(Matrix<T> other, BinaryOperator<T> plus) {
        Objects.requireNonNull(other, "Null found in plus - MatrixMap");
        Objects.requireNonNull(plus, "Null found in plus - MatrixMap");

        Map<Indexes, T> result = matrix.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> {
                    T currentValue = entry.getValue();
                    T otherValue = other.matrix().get(entry.getKey());

                    return calculateSum(currentValue, otherValue, plus);
                }));

        return new MatrixMap<>(result);
    }

    /**
     * Helper method for plus()
     * @param currentValue this matrix value
     * @param otherValue other matrix value
     * @param plus Operator to do summation between 2 values
     * @return value of the summation of 2 values or null
     * */
    private T calculateSum(T currentValue, T otherValue, BinaryOperator<T> plus) {
        if (currentValue == null)
            return (otherValue == null) ? null : plus.apply((T) Integer.valueOf(0), otherValue);
        else
            return (otherValue == null) ? currentValue : plus.apply(currentValue, otherValue);
    }

    /** Product of 2 matrices Matrix can be SparseMatrix or MatrixMap
     * @param other Matrix, can be SparseMatrix or MatrixMap
     * @param ring Type of ring of MatrixMap
     * @return product of this and other matrix
     * @throws NullPointerException if parameters are null
     * */
    @Override
    public MatrixMap<T> times(Matrix<T> other, Ring<T> ring) {
        Objects.requireNonNull(other, "Null found in times() - MatrixMap");
        Objects.requireNonNull(ring, "Null found in times() - MatrixMap");

        Map<Indexes, T> resultMap = Indexes.stream(this.size().row() - 1, other.size().col() - 1)
                .collect(Collectors.toMap(
                        index -> index,
                        index -> {
                            T result = ring.zero();
                            for (int i = 0; i < this.size().col(); i++) {
                                T thisValue = this.value(index.row(), i);
                                T otherValue = other.value(i, index.col());

                                if (thisValue == null || otherValue == null) {
                                    result = ring.sum(result, ring.zero());
                                } else {
                                    result = ring.sum(result, ring.product(thisValue, otherValue));
                                }
                            }
                            return result;
                        }
                ));


        return new MatrixMap<>(resultMap);
    }

    /**
     * Conversion between MatrixMap to SparseMatrix
     * @return new SparseMatrix with entries value of MatrixMap, and null become 0
     * */
    public SparseMatrix<T> toSparseMatrix() {
//        MatrixMap.NonSquareException.requireDiagonal(this.size());
        int rows = this.size().row();
        int cols = this.size().col();
        return SparseMatrix.instance(rows + 1, cols + 1, this::value);
    }

    /**
     * Error handling for if the MatrixMap has invalid length exception
     * */
    public static class InvalidLengthException extends Exception {
        /** Serial version UID of the Exception*/
        private final long serialVersionUID = 0;

        /** Cause of the exception */
        private final Cause cause;

        /** Length of the Matrix that caused exception */
        private final int length;

        /** Cause enumation of Exception */
        private enum Cause {
            /** ROW being cause*/
            ROW,
            /** COLUMN being cause*/
            COLUMN
        }

        /**
         * Constructor
         * @param cause Cause of the exception
         * @param length Length of the Matrix that caused exception
         * */
        public InvalidLengthException(Cause cause, int length) {
            this.cause = cause;
            this.length = length;
        }

        /**
         * Retrieve Cause message
         * @return Cause of the exception
         * */
        public Cause getTheCause() {
            return this.cause;
        }

        /**
         * Retrieve length
         * @return Length of the Matrix that caused exception
         * */
        public int getLength() {
            return this.length;
        }

        /**
         * Create new InvalidLengthException
         * @param cause Cause of the exception
         * @param length Length of the Matrix that caused exception
         * @return Length of the Matrix that caused exception
         * @throws IllegalArgumentException if the MatrixMapS is illegal
         * */
        public static int requireNonEmpty(Cause cause, int length) {
            if (checkNonEmpty(length)) throw new IllegalArgumentException(new InvalidLengthException(cause, length));
            return length;
        }

        /**
         * Helper method
         * @param val value at the entry
         * @return boolean value
         * */
        private static boolean checkNonEmpty(int val) {
            return (val <= 0) ? true : false;
        }
    }
}