#!/bin/bash

gradle -q  clean publish
gradle -q  component:clean

echo "TRACER: should publish:"
gradle -q  compileJava publish
