#!/bin/bash

find . -name "*.java" | xargs -n1 ./replace.sh 
