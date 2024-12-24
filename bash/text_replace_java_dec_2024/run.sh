#!/bin/bash

find . -name "*.java" > java.list.txt 

cat java.list.txt | xargs -n1 ./echo.sh 
