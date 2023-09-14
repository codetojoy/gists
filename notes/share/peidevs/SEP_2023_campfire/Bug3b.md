
### Bug 3b 

```
public void doFFMpegComputation() {
    PrintStream original = System.out;

    System.setOut(null);

    // call library
    doComputation();

    System.setOut(original);
}
```
