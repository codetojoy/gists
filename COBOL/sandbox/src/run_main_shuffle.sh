#!/bin/bash 

cobc -o knuth-shuffle knuth-shuffle.cbl

if [ $? -ne "0" ]
then
    echo "TRACER: error [knuth-shuffle.cbl] "
    exit -1
fi

cobc -x -o main_shuffle main_shuffle.cbl

if [ $? -ne "0" ]
then
    echo "TRACER: error [main_shuffle.cbl] "
    exit -1
fi

./main_shuffle

