
### Usage

* `gradle clean war`
* deploy to Tomcat
* note that there is no web UI
* the logs should read as follows:

```
catalina.out:TRACER custom override for JdkVersion! Accepting Java 1.8.x
catalina.out:TRACER hello from FooService.initialize()
catalina.out:TRACER hello from BarService.initialize() begin
catalina.out:TRACER Spring spoof start ...
catalina.out:TRACER Spring spoof OK ... obj: class org.springframework.transaction.annotation.AnnotationTransactionAttributeSource
catalina.out:TRACER hello from BarService.initialize() end
```

### Notes

* The `SpringSpoof` class is simply used to call a Spring class which uses `JdkVersion`. For Java 1.8, this will fail unless we have our own version of `JdkVersion`.
