
#!/bin/bash 

[ -z "$1" ] && echo "requires git hash" && exit 1;

# HASH=9534be3c
HASH=$1
SCRIPT_DIR=../..

cd utils/PomSearcher
groovy PomSearcher.groovy $HASH $SCRIPT_DIR
cd - 
HASH=$1

