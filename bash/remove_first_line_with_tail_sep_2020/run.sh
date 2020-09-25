#!/bin/bash 

set -e 

HOME_DIR=$PWD
IN_FILE=$HOME_DIR/data.csv

NUM_LINES=`wc -l $IN_FILE |  awk '{print $1}'`
echo $NUM_LINES
echo "TRACER num lines: ${NUM_LINES}"

NUM_LINES_NO_HEADER=$(( NUM_LINES - 1))
echo $NUM_LINES_NO_HEADER
echo "TRACER NEW num lines: ${NUM_LINES_NO_HEADER}"

tail -n $NUM_LINES_NO_HEADER data.csv > tmp_data.csv

