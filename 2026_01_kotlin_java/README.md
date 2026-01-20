# Java-Kotlin Interoperability Sandbox

A minimal project demonstrating bidirectional interoperability between Java and Kotlin.

## Prerequisites

- [SDKMan!](https://sdkman.io/) installed
- Java 21: `sdk install java 21-tem` (or similar)
- Kotlin 2.2.x: `sdk install kotlin 2.2.0`
- or run `sdk env`

## Quick Usage

- `./run_java.sh 1` uses Java to call `Animal.kts`
- `./run_kotlin.sh 1` uses Kotlin to call `Vehicle.java`

## Project Structure

```
src/
├── Animal.kt       # Kotlin class called from Java
├── Vehicle.java    # Java class called from Kotlin
├── JavaMain.java   # Java program → Kotlin
└── KotlinMain.kt   # Kotlin program → Java
```

## Usage

### Java calling Kotlin

```bash
./run_java.sh [type]
```

| Type | Sound |
|------|-------|
| 1    | Meow  |
| 2    | Woof  |
| 3    | Moo   |
| 4    | Oink  |

Example:
```bash
$ ./run_java.sh 2
Java calling Kotlin: Animal type 2 says 'Woof'
```

### Kotlin calling Java

```bash
./run_kotlin.sh [type]
```

| Type | Fuel     |
|------|----------|
| 1    | Electric |
| 2    | Gasoline |
| 3    | Diesel   |
| 4    | Hydrogen |

Example:
```bash
$ ./run_kotlin.sh 1
Kotlin calling Java: Vehicle type 1 uses 'Electric'
```
