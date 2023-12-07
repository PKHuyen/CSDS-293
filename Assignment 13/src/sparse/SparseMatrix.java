/**
 * @author Harley Phung
 * Email hkp15
 */
package sparse;

import Assignment2.Ring;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Assignment 12, SparseMatrix did not register 0 in its matrix, null in the entry
 * Demonstration of how Square SparseMatrix can create a ring
 * @param <T> Type of the SparseMatrixRing
 * */
public final class SparseMatrix<T> implements Matrix<T>{
    /** Sparse Matrix is a type of Map */
    private final Map<Indexes, T> matrix;

    /**
     * Constructor
     * @param matrix map of the sparseMatrix
     * */
    private SparseMatrix(Map<Indexes, T> matrix) {
        this.matrix = matrix;
    }

    /**
     * Return the sparseMatrix
     * @return map of the sparseMatrix
     * */
    public Map<Indexes, T> matrix() {
        return this.matrix;
    }

    /**
     * SparseMatrix in String format
     * @return string format of the sparseMatrix
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
     * Return size of the SparseMatrix
     * @return Indexes size of the SparseMatrix
     * @throws NullPointerException if sparseMatrix if null
     * */
    @Override
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
     * Value at indexes
     * @param indexes Indexes of the SparseMatrix
     * @return value of at the index entry
     * @throws NullPointerException if the index is out of bound or null
     * */
    @Override
    public T value(Indexes indexes) {
        Objects.requireNonNull(indexes, "Null found in value() - SparseMatrix");
        //Check if index out of bound
        return matrix.getOrDefault(indexes, (T) Integer.valueOf(0));
    }

    /**
     * Value at entry of matrix
     * @param row row of the entry
     * @param col column of the entry
     * @return value at the entry (row, col)
     * @throws NullPointerException if parameters are null
     * */
    @Override
    public T value(int row, int col) {
        Objects.requireNonNull(row, "Null found in value() - SparseMatrix");
        Objects.requireNonNull(col, "Null found in value() - SparseMatrix");
        return value(new Indexes(row , col));
    }

    /**
     * Instantiate a SparseMatrix
     * @param rows number of rows in total of sparseMatrix
     * @param columns number of columns in total of sparseMatrix
     * @param valueMapper function of the SparseMatrix
     * @param <S> Type of the sparseMatrix
     * @return new sparseMatrix that have dimension rows x columns
     * @throws NullPointerException if parameters are null
     * @throws InvalidLengthException if SparseMatrix has invalid length
     * */
    public static <S> SparseMatrix<S> instance(int rows, int columns, Function<Indexes, S> valueMapper) {
        Objects.requireNonNull(rows, "Null found in instance() - SparseMatrix");
        Objects.requireNonNull(columns, "Null found in instance() - SparseMatrix");
        Objects.requireNonNull(valueMapper, "Null found in instance() - SparseMatrix");

        SparseMatrix.InvalidLengthException.requireNonEmpty(SparseMatrix.InvalidLengthException.Cause.ROW, rows);
        SparseMatrix.InvalidLengthException.requireNonEmpty(SparseMatrix.InvalidLengthException.Cause.COLUMN, columns);

        Map<Indexes, S> resultMatrix = new LinkedHashMap<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Indexes index = new Indexes(i, j);
                S value = valueMapper.apply(index);
                if (!value.equals((S) Integer.valueOf(0))) {
                    resultMatrix.put(index, value);
                }
            }
        }

        return new SparseMatrix<>(resultMatrix);
    }

    /**
     * Instantiate a SparseMatrix
     * @param size indexes of the sparseMatrix (dimension)
     * @param valueMapper function of the SparseMatrix
     * @param <S> Type of the sparseMatrix
     * @return new sparseMatrix that have dimension rows x columns
     * @throws NullPointerException if parameters are null
     * */
    public static <S> SparseMatrix<S> instance(Indexes size, Function<Indexes, S> valueMapper) {
        Objects.requireNonNull(size, "Null found in instance() - SparseMatrix");
        Objects.requireNonNull(valueMapper, "Null found in instance() - SparseMatrix");

        return instance(size.row() + 1, size.col() + 1, valueMapper);
    }

    /**
     * Instantiate a square SparseMatrix
     * @param size indexes of the sparseMatrix (dimension)
     * @param value value in each entry of the SparseMatrix
     * @param <S> Type of the sparseMatrix
     * @return new sparseMatrix that have dimension size x size
     * @throws NullPointerException if parameters are null
     * */
    public static <S> SparseMatrix<S> constant(int size, S value) {
        Objects.requireNonNull(size, "Null found in constant() - SparseMatrix");
        Objects.requireNonNull(value, "Null found in constant() - SparseMatrix");

        return instance(size, size, indexes -> value);
    }

    /**
     * Instantiate an identity SparseMatrix
     * @param size indexes of the sparseMatrix (dimension)
     * @param zero value of the zero entry
     * @param identity value of the non-zero entry
     * @param <S> Type of the sparseMatrix
     * @return new SparseMatrix that have dimension size x size
     * @throws NullPointerException if parameters are null
     * */
    public static <S> SparseMatrix<S> identity(int size, S zero, S identity) {
        Objects.requireNonNull(size, "Null found in identity() - SparseMatrix");
        Objects.requireNonNull(zero, "Null found in identity() - SparseMatrix");
        Objects.requireNonNull(identity, "Null found in identity() - SparseMatrix");

        return instance(size, size, indexes -> indexes.areDiagonal() ? identity : zero);
    }

    /**
     * Instantiate a SparseMatrix from array
     * @param matrix 2-dimensional array of the sparseMatrix
     * @param <S> Type of the sparseMatrix
     * @return new SparseMatrix that have entries being value from the matrix
     * */
    public static <S> SparseMatrix<S> from(S[][] matrix) {
        int row = matrix.length;
        int col = matrix.length;

        return instance(row, col, indexes -> matrix[indexes.row()][indexes.col()]);
    }

    /** Sum of 2 matrices. Matrix can be SparseMatrix or MatrixMap
     * @param other other Matrix, can be SparseMatrix or MatrixMap
     * @param plus Operator to do summation between 2 matrices
     * @return new SparseMatrix of summation of 2 Matrices
     * @throws NullPointerException if parameters are null
     * */
    @Override
    public SparseMatrix<T> plus(Matrix<T> other, BinaryOperator<T> plus) {
        Objects.requireNonNull(other, "Null found in plus - SparseMatrix");
        Objects.requireNonNull(plus, "Null found in plus - SparseMatrix");

        Map<Indexes, T> result = matrix.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> {
                    T currentValue = entry.getValue();
                    T otherValue = other.matrix().get(entry.getKey());

                    return calculateSum(currentValue, otherValue, plus);
                }));

        return new SparseMatrix<>(result);
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
     * @param ring Type of ring of SparseMatrix
     * @return product of this and other matrix
     * @throws NullPointerException if parameters are null
     * */
    @Override
    public SparseMatrix<T> times(Matrix<T> other, Ring<T> ring) {
        Objects.requireNonNull(other, "Null found in times() - SparseMatrix");
        Objects.requireNonNull(ring, "Null found in times() - SparseMatrix");
        SparseMatrix.NonSquareException.requireDiagonal(this.size());

        Map<Indexes, T> resultMap = new HashMap<>();
        Indexes.stream(size().row() - 1, size().col() - 1)
                .forEach(index -> {
                    T result = ring.zero();
                    for (int i = 0; i < this.size().row(); i++) {
                        T thisValue = this.value(index.row(), i);
                        T otherValue = other.value(i, index.col());
                        if (thisValue == null || otherValue == null) result = ring.sum(result, (T) Integer.valueOf(0));
                        else result = ring.sum(result, ring.product(thisValue, otherValue));
                    }
                    resultMap.put(new Indexes(index.row(), index.col()), result);
                });

        return new SparseMatrix<>(resultMap);
    }

    /**
     * Conversion between SparseMatrix to MatrixMap
     * @return new MatrixMap with entries value of SparseMatrix, and null become 0
     * @throws NonSquareException if this SparseMatrix is not square
     * */
    public MatrixMap<T> toMatrixMap() {
        SparseMatrix.NonSquareException.requireDiagonal(this.size());
        int rows = this.size().row();
        int cols = this.size().col();
        return MatrixMap.instance(rows+1, cols+1, this::value);
    }

    /**
     * Error handling for if the SparseMatrix has invalid length exception
     * */
    public static class InvalidLengthException extends Exception {
        /** Serial version UID of the Exception*/
        private final long serialVersionUID = 0;

        /** Cause of the exception */
        private final SparseMatrix.InvalidLengthException.Cause cause;

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
        public InvalidLengthException(SparseMatrix.InvalidLengthException.Cause cause, int length) {
            this.cause = cause;
            this.length = length;
        }

        /**
         * Retrieve Cause message
         * @return Cause of the exception
         * */
        public SparseMatrix.InvalidLengthException.Cause getTheCause() {
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
         * @throws IllegalArgumentException if the SparseMatrix is illegal
         * */
        public static int requireNonEmpty(SparseMatrix.InvalidLengthException.Cause cause, int length) {
            if (checkNonEmpty(length)) throw new IllegalArgumentException(new SparseMatrix.InvalidLengthException(cause, length));
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

    /**
     * Error handling if the matrix is non-square
     * */
    public static class NonSquareException extends IllegalStateException {
        /** Indexes of the matrix */
        private final Indexes indexes;

        /**
         * Constructor
         * @param indexes indexes of the matrix
         * */
        public NonSquareException(Indexes indexes) {
            this.indexes = indexes;
        }

        /**
         * Return indexes of the matrix
         * @return indexes of the matrix
         * */
        public Indexes getIndexes() {
            return indexes;
        }

        /**
         * Check if the Matrix is diagonal
         * @param indexes indexes of the matrix
         * @return new Indexes with row x col dimension where row = col
         * @throws IllegalStateException if the size is not match
         * */
        public static Indexes requireDiagonal(Indexes indexes) {
            if (indexes.areDiagonal())
                return new Indexes(indexes.row(), indexes.col());
            throw new IllegalStateException("Size not match");
        }
    }
}
