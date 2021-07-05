#!/bin/bash

find . -name "*.java" -exec grep -il $1 {} \; 
