#!/bin/bash 

echo "running 2 phases in quiet mode ..."
gradle -q test

echo "phase1 OK"

gradle -q run > tmp.log ; diff tmp.log golden.log

echo "phase 2 OK"
