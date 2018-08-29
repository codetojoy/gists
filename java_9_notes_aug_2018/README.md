
### Java 8

* Large JDK. All classes in a bulky, monolithic `rt.jar`
    * You get Swing, CORBA, etc even if you don't want them.
* Relatively slow start-up time.
* Third-party libraries use reflection in an unrestricted way.
    * Reflection is used for internal JDK methods, which Oracle hates because it slows innovation.

### Java 9

* The JDK itself is partitioned into modules.
    * No more `rt.jar`. The JDK runtime looks very different.
    * Java EE, CORBA modules are shipped with the JDK, but must be explicitly added.
* Apps can be partitioned using the same system: use a modulepath instead of a classpath.
    * Benefits: smaller runtime, better encapsulation, better performance (in the future) 
    * NOTE: apps can stay on the classpath! Modularity is not mandatory for apps.
* Reflection
    * Still allowed, but must be explicitly granted. 
    * For internal JDK methods, there is a warning about `illegal access` and that the code will eventually break when enforced "for real".
    * We see this most often with third-party libraries such as Spring, Hibernate, etc. Almost all libraries have new versions that work with Java 9 (i.e. no warnings).

### Java 11

* Java EE and CORBA modules have been removed from the runtime. Apps must use external jars for this functionality. e.g. JAXB
* It appears that reflection is still issuing warnings and that illegal access is granted. 

### Resources

* [Why Jigsaw/JPMS](https://stackoverflow.com/questions/11844829/why-project-jigsaw-jpms)
    * Jigsaw is the module system introduced in Java 9 
* [Benefits of Jigsaw for Small Apps](https://stackoverflow.com/questions/45655210/benefits-of-jpms-project-jigsaw-for-small-applications-libraries)
* massive [list of resources](https://github.com/codetojoy/talk_maritimedevcon_java_9_modules)
    * presentation slides [here](https://docs.google.com/presentation/d/1d2qwIx5tg_GWUa2Amz4fiqaHU4DTY22nvsTMVlrz1eY/edit#slide=id.p)
* relatively new video on [Java 9 modules](https://www.youtube.com/watch?v=eTcjU5JlEpI)
