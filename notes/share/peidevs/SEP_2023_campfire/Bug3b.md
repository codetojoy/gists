
### Bug 3b 

```
if (loginOk) {
    System.out.println("TRACER login is ok");
    forwardToHomePage();
} else {
    logger.error("login denied. username: " + username);
}
```

```
public void doFFMpegComputation() {
    PrintStream original = System.out;

    // we can NEVER use System.out.println !
    System.setOut(null);

    // call library
    doComputation();

    System.setOut(original);
}
```
