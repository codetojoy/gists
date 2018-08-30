
### To Run

* execute: `prepare_build_run.sh`

### Notes

* This creates a module, `net.codetojoy`, which opens the `net.codetojoy` package for reflection.
* The `jackson-databind` jar is used as an automatic module in `mjars` folder.
* Other dependency jars reside in the `jars` folder on the classpath.
* Note that `App.java` is unchanged from the classpath version.
* Note that there are `services` in JPMS which are another option for discovering `ObjectMapper`.

