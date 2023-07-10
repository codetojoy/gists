
### summary

* Josh Bloch
* 3rd edition, Addison-Wesley

### notes

### ch 1

* java.util, java.io, java.util.concurrent, java.util.function
* know when to break the rules

### ch 2: creating, destroying objects

* 1. static factory methods 
* named, virtual, can cache, service providers
* limit: can't subclass
* examples

* 2. builder pattern for many params
* better than telescopic ctor and Java Beans
* e.g. nutrition

* 3. enforce singleton with private ctor  
* watch out for serialization
* single-value enum is the best way (serialization for free)
* reflection attacks
* e.g. Elvis

* 4. enforce non-instantability with private ctor

* 5. dependency injection: ctor params
* e.g. Lexicon

* 6. don't create many objects needlessly
* e.g. prefer Pattern.matcher(s).matches over s.matches(regex)  
* autoboxing
* database connection makes sense for object pooling 
* defensive copying is OK 

* 7. obsolete object references
* e.g. user-managed stack

* 8. avoid cleaners and finalizers  

* 9. try with resources
* before: hard to get right
* before: multiple exceptions, masking the problem

### ch 3: methods common to all objects 

* 10. contract for equals 
* reflexive, symmetric, transitive, etc
* no way to extend class with value and preserve equals
    - color / point 
    - motivates composition
* good discussion, thorough

* 11. hashcode
* whenever override equals, for collections  

* 12. toString()

* 13. clone()

* 14. comparable
* same restriction on derived classes as equals
    - i.e. need composition with a view method
    - otherwise violates Liskov
* BigDecimal has different values for equals and compare
    - e.g. 1.00 vs 1.0 

### ch 4 classes, interfaces

* 15. minimize access, encapsulation
* default package ok for testing
* protected is considered part of the API
* counter: getters/setters are not really encapsulation 
* avoid modules (!!) 

* 16. accessor methods not public fields

* 17. minimize mutability

* 18. composition > inheritence
* inheritence breaks encapsulation

* 19. design and document for inheritence (or inhibit)

* 20. prefer interfaces over abstract classes

* 21. design interfaces for posterity
* Apache Commons example re: adding default methods to interface

* 22. interfaces are for types
* don't use interface for constants

* 23. prefer class hierarchies over discriminants

* 24. favour static member classes over nonstatic
* good review

* 25. limit source files to single top-level class
* compilation weirdness a la puzzlers
* i'm definitely contrarian on this one re: tests

### ch 5 generics

* 26. no raw types
* e.g. `List` 
* unbounded List<?> : can't insert or do anything with elements
    - much safer than List
* List<T>.class is illegal: can't use generics in class literals
* also `instanceof`

* 27. unchecked warnings
* remove all warnings
* be sure to check type-safety
* use smallest scope possible 

* 28. prefer lists over arrays
* arrays are covariant: if T < S then T[] < S[]
* lists are invariant
* arrays are reified ; lists have erasure 
* no generic arrays 
* arrays: runtime safety; generics: compile-time safety
* they don't mix well; var-args can get tricky

* 29. prefer generic types
* slower than arrays but safer, esp. compile-time
* casts are a smell

* 30. prefer generic methods
* recursive type bound re: Comparable 

* 31. Use bounded wildcards to increase API flexibility
* generics are invariant: List<Number> vs List<Integer>
* but Integer is a Number, so List<? extends E> addresses this
* note that here E is a subtype of itself
* "? extends E" -> some subtype of E
* "? super E" -> some supertype of E
* PECS rule
* no bounded wildcards in return types
* great examples
* if type param appears once: use List<?>
* method to capture the type

* 32. generics and varargs
* @Safevarargs annotation

* 33. consider typesafe heterogeneous containers
* don't parameterize the map, parameterize the key
* Class<T> is generic
* when we need compile-time and run-time: type token
* e.g. Favourites 
* really good example

### ch 6 enums, annotations

* 34. prefer enums over int constants
* basic but good
* interesting about changing enums and re-compiling clients 
* constant-specific method impls

* 35. prefer instance fields over ordinals

* 36. prefer EnumSet over bit fields

* 37. prefer EnumSet over ordinal indexing

* 38. extensible enums with interfaces
* e.g. opcodes for a calculator

* 39. prefer annotations over naming patterns
* e.g. JUnit4 @Test vs `public void testFoo`
* long annotation processor example  

* 40. @Override

* 41. use marker interfaces to define types
* e.g. Serializable
* discussion on marker interface vs marker annotation

### ch 7 lambdas and streams

* 42. prefer lambdas over anon classes
* omit types of lambda params
* enum example: lambdas are SAMs right? 
    - DoubleBinaryOperator
* concern when lambdas are > 3 lines
* lambdas are not serializable

* 43. prefer method references over lambdas
* merge() method on Map ! 
* great table on pg 198 

* 44. use standard functional interfaces
* great table on pg 200 
* discussion on when to write one: e.g. Comparator
* use @FunctionalInterface, analogous to @Override

* 45. use streams judiciously 
* pipelines invoked when terminal operation is invoked
* anagram example with stream overdose
* balanced, happy medium 
* avoid using streams to process char values
* Mersenne primes, Cards

* 46. prefer no side-effects
* e.g. groupingBy 
* avoid `forEach`
* collector is a reduction strategy
* most of complexity is involves maps
* groupingBy: classifier function

* 47. prefer Collection over Stream as return type (!)
* Collection is both Iterable and has a stream() method

* 48. caution with parallel
* liveness concerns
* danger: source from iterate or limit
* best used for ArrayList and HashMap
* accumulator, combiner functions have a contract
    - if violated, parallel will expose them
* common fork-join pool 

### ch 8 methods

* 49. check parameters
* Objects.requireNonNull() 
* assertions for non-public methods

* 50. defensive copies
* make copies early, before checking parameters: TOCTOU attack 

* 51. design method signatures
* # args <= 4

* 52. overload judiciously
* override: dynamic selection
* overload: static selection at compile-time

* 53. varargs judiciously

* 54. return empty collections, not null

* 55. return Optional judiciously  
* similar to checked exceptions
* be careful with `isPresent()`
* OptionalInt, OptionalLong

* 56. doc comments

### ch 9 general programming

* 57. minimize scope of local vars
* keep methods small and focused

* 58. for each loop

* 59. know & use libraries

* 60. avoid float, double   

* 61. prefer primitive types over boxed 

* 62. avoid strings as types

* 63. string concatenation

* 64. prefer interfaces as references

* 65. be wary of reflection

* 66. be wary of native methods

* 67. optimize judiciously 

* 68. naming conventions

### ch 10 exceptions

* 69. use only for exceptional conditions

* 70. checked exceptions, unchecked, errors

* 71. avoid liberal use of checked exceptions

* 72. prefer standard exceptions

* 73. exception translation
* alter ex to current level of abstraction

* 74. document exceptions

* 75. capture details in failure messages

* 76. failure atomicity
* object should be useable after an exception
* not always possible: threads

* 77. don't ignore exceptions

### ch 11 concurrency

* 78. synchronize access to mutable data

* 79. avoid excess sync
* never call alien/client in sync block
* observer example
* deadlock, safety, etc

* 80. prefer executors, tasks, streams

* 81. prefer new utilities over wait/notify
* System.nanoTime 

* 82. document thread safety

* 83. lazy init judiciously

* 84. don't depend on the thread scheduler

### ch 12 serialization

* 85. prefer alternatives
* huge attack surface
    - gadget, gadget chain
* deserialization bomb
* use JSON or protobuf

* 86. serialize with caution
* part of the API
* versioning
* components
* inheritence, inner classes

* 87. custom serialized form
* various gotchas and constraints

* 88. readObject
* inside baseball
* serialization is a public, extralinguistic constructor
* defensive copies, many other gotchas

* 89. beware of readResolve

* 90. serialization proxy
* a DTO that acts as a bulkhead 
* various good practices around serialization

### brain-storm

* reasonable for interviews
* read the JLS
* good examples of errors in Java
* some idioms are declared 'obsolete' but some of the current items are obsolete
* Java 18 has more on finalizers/cleaners
* what is after Java 9, aside from syntactic sugar?
    - records
    - 9: default methods in interfaces
    - pattern matching and data-oriented programming
        - ADTs
    - libraries (one of the items)
    - ThreadLocal
    - object creation, String efficiency
    - checked exceptions
* i am a contrarian
    - 25 one source top-level source file 
    - 45 use streams judiciously
* i need to pay attention
    - use `isPresent()` carefully 
    - collectors in streams
    - Integer.parseInt()
* for review
    - BigDecimal, 1/10
    - auto-boxing
    - try-with-resources
    - @Suppress for Mockito
* themes
    - prefer errors at compile-time
    - generics are as crazy as ever
    - focus on writing APIs
        - use this as a reference
    - streams are functional, new paradigm
    - puzzles a la Java Puzzler
* best parts
    - generics
    - lambdas
    - streams

### resources

### questions

* what is AutoValue
    - Google, open-source


