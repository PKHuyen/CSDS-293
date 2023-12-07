/**
 * @author Harley Phung
 * Email hkp15
 */
package sparse;

import Assignment2.Ring;

import java.util.Objects;

/**
 * Demonstration of how Square SparseMatrix can create a ring
 * @param <T> Type of the SparseMatrixRing
 * */
public class SparseMatrixRing<T> implements Ring<SparseMatrix<T>>{
    /** Type of ring of SparseMatrix*/
    private Ring<T> ring;

    /**
     * Constructor
     * @param ring Type of ring of SparseMatrix
     * */
    public SparseMatrixRing(Ring<T> ring) {
        this.ring = ring;
    }

    /**
     * Return all zero sparseMatrix
     * @return additive identity SparseMatrix
     * */
    @Override
    public SparseMatrix<T> zero() {
        T[][] zeroArray = (T[][]) new Object[1][1];
        return SparseMatrix.from(zeroArray);
    }

    /**
     * Return identity Matrix
     * @return multiplicative identity SparseMatrix
     * */
    @Override
    public SparseMatrix<T> identity() {
        return SparseMatrix.identity(1, ring.zero(), ring.identity());
    }

    /**
     * Calculate sum between 2 matrices
     * @param x First SparseMatrix
     * @param y Second SparseMatrix
     * @return summation of x and y
     * @throws NullPointerException if parameters are null
     * */
    @Override
    public SparseMatrix<T> sum(SparseMatrix<T> x, SparseMatrix<T> y) {
        Objects.requireNonNull(x, "Null found in sum() - SparseMatrixRing");
        Objects.requireNonNull(y, "Null found in sum() - SparseMatrixRing");
        return x.plus(y, ring::sum);
    }

    /**
     * Calculate product between 2 matrices
     * @param x First SparseMatrix
     * @param y Second SparseMatrix
     * @return product of x and y
     * @throws NullPointerException if parameters are null
     * */
    @Override
    public SparseMatrix<T> product(SparseMatrix<T> x, SparseMatrix<T> y) {
        Objects.requireNonNull(x, "Null found in product() - SparseMatrixRing");
        Objects.requireNonNull(y, "Null found in product() - SparseMatrixRing");
        return x.times(y,new SparseMatrixRing(ring));
    }
}
