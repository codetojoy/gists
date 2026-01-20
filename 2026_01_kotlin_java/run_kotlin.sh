#!/bin/bash

export SDKMAN_DIR="$HOME/.sdkman"
[[ -s "$HOME/.sdkman/bin/sdkman-init.sh" ]] && source "$HOME/.sdkman/bin/sdkman-init.sh"

# Your SDKMAN! commands go here

# Kotlin calling Java example
# Requires Java 21 and Kotlin 2.2.x via SDKMan!

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
SRC_DIR="$SCRIPT_DIR/src"
OUT_DIR="$SCRIPT_DIR/out"

# Clean and create output directory
rm -rf "$OUT_DIR"
mkdir -p "$OUT_DIR"

echo "Compiling Java class (Vehicle.java)..."
javac -d "$OUT_DIR" "$SRC_DIR/Vehicle.java"

echo "Compiling Kotlin class (KotlinMain.kt)..."
kotlinc "$SRC_DIR/KotlinMain.kt" -cp "$OUT_DIR" -d "$OUT_DIR"

echo "Running Kotlin program..."
kotlin -cp "$OUT_DIR" KotlinMainKt "$@"
