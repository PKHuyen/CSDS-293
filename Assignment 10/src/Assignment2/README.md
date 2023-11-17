# CSDS 293 - Assignment 2 Instruction - Updated

## Requirements
* Junit 5.* in ClassPath.
* Use Intellij for convenience (they implemented the needed class paths for user)

## Guidelines
* Open Assignment2_CS293 in Intellij and wait for indexing and building
* There's a folder named "Test" that contained all the tests for IntegerRing, DoubleRing, BigIntegerRing.
* Add JUnit 5.* to class path if Assignment2.Test.IntegerRingTest, Assignment2.Test.DoubleRingTest, BigIntegerTest did not compile.
  * You can click on BigIntegerTest, in the import section, click Junit and choose add to class path
* Run the test.

## Interface Assignment2.Ring
This interface is abstract and a base for Assignment2.IntegerRing, Assignment2.DoubleRing, and Assignment2.BigIntegerRing classes to implement and override the functions

* zero() return the additive identity 0
* identity() return the multiplicative identity 1
* sum(T x, T y) return the addition of x and y
* product(T x, T y) return the multiplication of x and y

Currently, no test is provided

## Class Assignment2.Rings - Utilities
This class served as utility for Assignment2.Ring to support common operations on Assignment2.Ring

* reduce(List<T> args, T zero, BinaryOperator<T> accumlator) return the aggregated elements from a List of arguments and start point of zero
* sum(List<T> args, Assignment2.Ring<T> ring) return the summation of args according to sum() of given ring
* product(List<T> args, Assignment2.Ring<T> ring) return the miltiplication of args according to product() of given ring

Currently, no test is provided

## Class Assignment2.IntegerRing
This class is implemented from Interface Assignment2.Ring, override the functions based on the Integer type

* zero() return the additive identity 0
* identity() return the multiplicative identity 1
* sum(T x, T y) return the addition of x and y
* product(T x, T y) return the multiplication of x and y

For error handling, for each method sum() and product(), if there's at least one null input, have to throw NullPointerException() with a message "Null found in input - sum() / product() - Assignment2.IntegerRing" to find which exact methods and exact class that has error.

Tests are provided in Assignment2.Test.IntegerRingTest

## JUnit Assignment2.Test.IntegerRingTest
For testing, each of the method has test

* zero() and identity() only return 0 and 1 respectively, so only one test is created in these method
* sum(): Test with 2 non negative integer,
  1 non negative integer with 1 negative integer
  2 negative integer
  Error handling when 1 input is null
  Error handling when 2 inputs are null

To test, use your familiar IDE (should be Intellj). After download the package, make sure that there's no error shown when building the project (add classpath for JUnit 5.* if needed). Hit Run and see if the test is good.


## Class Assignment2.DoubleRing
This class is implemented from Interface Assignment2.Ring, override the functions based on the Double type

* zero() return the additive identity 0.0
* identity() return the multiplicative identity 1.0
* sum(Double x, Double y) return the addition of x and y
* product(Double x, Double y) return the multiplication of x and y

For error handling, for each method sum() and product(), if there's at least one null input, have to throw NullPointerException() with a message "Null found in input - sum() / product() - Assignment2.DoubleRing" to find which exact methods and exact class that has error.


## JUnit Assignment2.Test.DoubleRingTest
For testing, each of the method has test

* zero() and identity() only return 0.0 and 1.0 respectively, so only one test is created in these method
* sum(): Test with 2 non negative double,
  1 non negative double with 1 negative double
  2 negative double
  Error handling when 1 input is null
  Error handling when 2 inputs are null

To test, use your familiar IDE (should be Intellj). After download the package, make sure that there's no error shown when building the project (add classpath for JUnit 5.* if needed). Hit Run and see if the test is good.

## Class Assignment2.BigIntegerRing
This class is implemented from Interface Assignment2.Ring, override the functions based on the BigInteger type

* zero() return the additive identity 0
* identity() return the multiplicative identity 1
* sum(BigInteger x, BigInteger y) return the addition of x and y
* product(BigInteger x, BigInteger y) return the multiplication of x and y

For error handling, for each method sum() and product(), if there's at least one null input, have to throw NullPointerException() with a message "Null found in input - sum() / product() - Assignment2.BigIntegerRing" to find which exact methods and exact class that has error.


## JUnit Assignment2.Test.BigIntegerRingTest
For testing, each of the method has test

* zero() and identity() only return 0 and 1 respectively, so only one test is created in these method
* sum(): Test with 2 non negative big integer,
  1 non negative big integer with 1 negative big integer
  2 negative big integer
  Error handling when 1 input is null
  Error handling when 2 inputs are null

To test, use your familiar IDE (should be Intellj). After download the package, make sure that there's no error shown when building the project (add classpath for JUnit 5.* if needed). Hit Run and see if the test is good.
