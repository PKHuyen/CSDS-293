# Assignment 10 - Defensive Programming
## Class: CSDS 293
## Author: Harley Phung - hkp15

### MatrixMap
* After changing Map/HashMap to NavigableMap/RoamingMap, the following methods is used and needed to be overriden in class RoamingMap
  * keySet():
  * entrySet():
  * get():
  * put(K key, V value)

### RoamingMap
* This is placeholder Map that implements NavigableMap
* All classes from NavigableMap are overriden and return null

### RoamingBarricade
* Basically RoamingMap but will override keySet(), entrySet(), get(), put() so that it returns and handles errors.
  * keySet(K key): The set's iterator returns the keys in ascending order. The set supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Set.remove, removeAll, retainAll, and clear operations. It does not support the add or addAll operations.
  * entrySet(): The set's iterator returns the entries in ascending key order.  The set supports element removal, which removes the corresponding mapping from the map, via the Iterator.
  * get(K key): Return the value of the map at key position. If there's no value from the key, log warning and try again. If there's exception, log exception warning, retry, then if null/exception, return null.
  * put(K key, V value): 
    * No error occured and everything works. At the key position of RoamingMap, there's correct value put in 
    * If key already contain value, return null, log warning
    * If key-value cannot be added to RoamingMap, return null, log warning, handle Exception
    * Fail to return value from the non-null key. Log warning and retry.

