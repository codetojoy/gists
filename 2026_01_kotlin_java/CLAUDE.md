# CLAUDE.md

## Project Overview

Java-Kotlin interoperability sandbox demonstrating bidirectional calls between the two languages.

## Tech Stack

- Java 21 (Amazon Corretto)
- Kotlin 2.2.21
- SDKMan! for version management

## Project Structure

- `src/Animal.kt` - Kotlin class called from Java (returns `Sequence<String>` for locations)
- `src/Vehicle.java` - Java class called from Kotlin (returns `ArrayList<String>` for locations)
- `src/JavaMain.java` - Java entry point
- `src/KotlinMain.kt` - Kotlin entry point
- `src/locations.txt` - 20 sample locations used by both demos
- `run_java.sh` - Compiles and runs Java→Kotlin demo
- `run_kotlin.sh` - Compiles and runs Kotlin→Java demo
- `out/` - Compiled class files (generated)

## Commands

```bash
sdk env install   # Install required SDK versions
sdk env           # Switch to project versions
./run_java.sh     # Run Java calling Kotlin
./run_kotlin.sh   # Run Kotlin calling Java
```

## Conventions

- No build tool (Gradle/Maven) - raw compiler invocation for simplicity
- Scripts initialize SDKMan! to ensure correct versions
- Output directory is cleaned on each run
