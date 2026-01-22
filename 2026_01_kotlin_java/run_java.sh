#!/bin/bash

export SDKMAN_DIR="$HOME/.sdkman"
[[ -s "$HOME/.sdkman/bin/sdkman-init.sh" ]] && source "$HOME/.sdkman/bin/sdkman-init.sh"

# Your SDKMAN! commands go here

# Java calling Kotlin example
# Requires Java 21 and Kotlin 2.2.x via SDKMan!

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
SRC_DIR="$SCRIPT_DIR/src"
OUT_DIR="$SCRIPT_DIR/out"

# Clean and create output directory
rm -rf "$OUT_DIR"
mkdir -p "$OUT_DIR"

echo "Compiling Kotlin class (Animal.kt)..."
kotlinc "$SRC_DIR/Animal.kt" -d "$OUT_DIR"

echo "Compiling Java class (JavaMain.java)..."
KOTLIN_LIB="$(dirname $(which kotlinc))/../lib/kotlin-stdlib.jar"
javac -cp "$OUT_DIR:$KOTLIN_LIB" -d "$OUT_DIR" "$SRC_DIR/JavaMain.java"

echo "Running Java program..."
java -cp "$OUT_DIR:$(dirname $(which kotlinc))/../lib/kotlin-stdlib.jar" JavaMain "$@"
