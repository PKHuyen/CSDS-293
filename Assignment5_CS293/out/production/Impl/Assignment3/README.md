# CSDS 293 - Assignment 3 Instruction

## Requirements
* Junit 5.* in ClassPath.
* Use Intellij for convenience (they implemented the needed class paths for user)

## Guidelines
* There's 2 folders submitted. 
  * The first one is Assignment2, which contained fixed (according to TA's suggestions) code for assignment 2.
  * The second one is Assignment3, which contained PolynomialRing, Polynomial and Tests for assignment 3.
    * The PolynomialRing<BigInteger> and PolynomialRing<Double> worked as expected but not PolynomialRing<Polynomial<Integer>>

## Polynomial Class
* coefficients field that stores the List<T> of coefficients
* Private Constructor that initiate the coefficients
* from() create new Polynomial with given coefficients
* getList(): retrieve the list of coefficients
* toString(): Override toString() to return the list of coefficients
* iterator(): Override iterator from Iterable Interface
* listIterator(): return the listIterator of coefficients at index i
* plus(): Implement the addition function of Polynomial
* times(): Implement the multiplication function of Polynomial
  * timesHelper(): private helper method of times
  * check(): private helper method of timesHelper()

## PolynomialRing class
* ring field that stores the ring which was initiated in the constructor
* private Constructor: Initiate the ring will be used
* instance(): create new PolynomialRing with given ring
* zero(): Override zero() from Ring that return empty List
* identity(): Override identity() from Ring that return list of identity of the ring
* sum(): Override sum() from Ring that implement addition function from Polynomial
* product(): Override product() from Ring that implement multiplication function from Polynomial

## BigIntegerPolynomialRingTest
* The test included all test for zero(), identity(), sum(), product()
  * zero() should return empty list
  * identity() should return list of identity of ring
  * sum() should return the sum of each element of the same index to each other
  * product() should return the product of backward/forward index of 2 Polynomial

## DoublePolynomialRingTest
* The test included all test for zero(), identity(), sum(), product()
  * zero() should return empty list
  * identity() should return list of identity of ring
  * sum() should return the sum of each element of the same index to each other
  * product() should return the product of backward/forward index of 2 Polynomial

## IntegerPolynomialRingTest
* This test only works when using toString() to avoid different types conversion
* The test included all test for zero(), identity(), sum(), product()
  * zero() should return empty list
  * identity() should return list of identity of ring
  * sum() should return the sum of each element of the same index to each other
  * product() should return the product of backward/forward index of 2 Polynomial
