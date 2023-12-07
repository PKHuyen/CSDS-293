package sparse;

import Assignment2.Ring;

import java.util.Map;
import java.util.function.BinaryOperator;

/**
 * Interface. contains all non-static methods in SparseMatrix and MatrixMap
 * @param <T> Type of the Matrix
 * */
public interface Matrix<T> {

    /**
     *  Get size of Matrix
     * @return the indexes size of the matrix
     *  */
    Indexes size();

    /**
     * Get matrix
     * @return the map of indexes of the matrix
     * */
    Map<Indexes, T> matrix();

    /**
     * Get value of index of matrix
     * @param indexes the indexes of the matrix
     * @return the value at the indexes of the matrix
     *  */
    T value(Indexes indexes);

    /**
     *  Get value of entry of matrix
     * @param row the row of the entry of the matrix
     * @param col the column of the entry of the matrix
     * @return the value at the entry (row, col) of the matrix
     * */
    T value(int row, int col);

    /**
     * Calculate sum between 2 matrices
     * @param other the other matrix
     * @param plus the operator to do summation between 2 matrices
     * @return new Matrix that is the result of 2 matrices summation
     * */
    Matrix<T> plus(Matrix<T> other, BinaryOperator<T> plus);

    /**
     * Calculate product between 2 matrices
     * @param other the other matrix
     * @param ring the ring that 2 matrices on
     * @return new Matrix that is the result of 2 matrices product
     * */
    Matrix<T> times(Matrix<T> other, Ring<T> ring);
}
