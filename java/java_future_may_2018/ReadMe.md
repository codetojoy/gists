
### Summary

* `FooService` uses a `BarService`, which is expensive to initialize
* `FooService` delegates to a `AsyncBarServiceWrapper`
* the wrapper returns a `Future<BarService>`. The `BarService` can be obtained via `get()`, which will block if not ready.
