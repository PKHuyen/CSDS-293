# Project 9 - CSDS 293
## Author: Harley Phung

### Notes: 
- Diff Files are generated using diff <original> <fixed> > <fileName>

### Landscape class
* Bug 1: 
  * Original:```private static final Logger logger = Logger.getLogger(Modification.class.getName());```
  * Fixed: ```private static final Logger logger = Logger.getLogger(Landscape.class.getName());```
  * Modification is changed to Landscape: Have to target to Landscape because this is the main class to be run
* Bug 2: 
  * Original: 

    ```
    switch (operation) {
    case RAISE:
    IntStream.range(x1, x2).forEach(num -> {
    landscape.put(num, landscape.getOrDefault(num, 0) + 1);
    });
    case DEPRESS:
    IntStream.range(x1, x2).forEach(num -> {
    landscape.put(num, landscape.getOrDefault(num, 0) - 1);
    });
    case HILL:
    createHill(x1, x2);
    case VALLEY:
    createValley(x1, x2);
    default:
    logger.log(Level.WARNING, "unsupported Operation");
    }
    }
    ```

  * Fixed:
    ```
    private final Map<Operation, BiConsumer<Integer, Integer>> opHandler = new EnumMap<>(Operation.class);
    public Landscape() {
    opHandler.put(Operation.RAISE, this::raiseHill);
    opHandler.put(Operation.DEPRESS, this::depressHill);
    opHandler.put(Operation.HILL, this::createHill);
    opHandler.put(Operation.VALLEY, this::createValley);
    }
    ```

  * The complexity of original code is 4, which is high, so I decided to use ```Map<>()``` and have the constructor to handle cases.
  * Another bug in original code is lack of ```break``` in cases
* Bug 3:
  * Original:
```
Optional.of(x1 > x2).filter(bool -> !bool)
                .ifPresent(bool -> logger.log(Level.WARNING, "x1 cannot be greater than x2; returning early")); 
```
  * Fixed: 
``` 
Optional.of(x1 > x2).filter(bool -> bool).
                ifPresent(bool -> logger.log(Level.WARNING, "x1 cannot be greater than x2; returning early"));
```
  * bool -> !bool will invert all the function. should be bool -> bool
* Bug 4
  * Original: 
```
  IntStream.range(x1, x2).forEach(num -> {
                    landscape.put(num, landscape.getOrDefault(num, 0) + 1);
                });
```
  * Fixed:
```
private void raiseHill(int x1, int x2) {
IntStream.rangeClosed(x1, x2).
                forEach(height -> landscape.put(height, landscape.getOrDefault(height, 0) + 1));
```
  * range() will not cover x2. rangeClosed() cover x2.
* Bug 5: 
* Original:
```
  IntStream.range(x1, x2).forEach(num -> {
                    landscape.put(num, landscape.getOrDefault(num, 0) + 1);
                });
```
* Fixed:
```
private void depressHill(int x1, int x2) {
IntStream.rangeClosed(x1, x2).
                forEach(height -> landscape.put(height, landscape.getOrDefault(height, 0) + 1));
```
* range() will not cover x2. rangeClosed() cover x2.
* Bug 6:
  * Original:
```
while (x2 - x1 > numPointsBetween) {
            landscape.put(x1 + offset, offset + 1);
            numPointsBetween++;

            if (x1 + offset != x2 - offset) {
                landscape.put(x2 - offset, offset + 1);
                numPointsBetween++;
            }

            offset++;
        }
```
* Fixed: 
```
while (x2 - x1 >= numPointsBetween) {
            landscape.put(x1 + offset, offset + 1);
            landscape.put(x2 - offset, offset + 1);
            offset++;
            numPointsBetween += 2;
        }
```
* x2 - x1 > numPointsBetween doesn't cover if x2 - x1 is even number (there's peak)

* Bug 7:
* Original:
```
while (x2 - x1 > numPointsBetween) {
            landscape.put(x1 + offset, -offset - 1);
            numPointsBetween++;

            if (x1 + offset != x2 - offset) {
                landscape.put(x2 - offset, -offset - 1);
                numPointsBetween++;
            }

            offset++;
        }
```
* Fixed:
```
while (x2 - x1 >= numPointsBetween) {
            landscape.put(x1 + offset, -offset - 1);
            landscape.put(x2 - offset, -offset - 1);
            offset++;
            numPointsBetween += 2;
        }
```
* x2 - x1 > numPointsBetween doesn't cover if x2 - x1 is even number (there's peak)
* Bug 8: Decided not to change the parameter because the instruction said no change.
* Original: 
```agsl
modifications.forEach(modification -> {
            Optional.ofNullable(modification)
                    .ifPresentOrElse(modification1 ->
                            modify(modification1.x2, modification1.x1, modification1.operation),
                            () -> logger.log(Level.WARNING, "modification is null, skipping"));
        });
```
* Fixed: 
```agsl
modifications.forEach(modification -> {
            Optional.ofNullable(modification)
                    .ifPresentOrElse(modification1 ->
                            modify(modification1.x1, modification1.x2, modification1.operation),
                            () -> logger.log(Level.WARNING, "modification is null, skipping"));
        });
```
* x2 was placed in front of x1.


### LandscapeTest:
* Add more test cases to cover newly private method, corner cases.