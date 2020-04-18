#!/bin/bash 

cobc -o knuth-shuffle knuth-shuffle.cbl

if [ $? -ne "0" ]
then
    echo "TRACER: error [knuth-shuffle.cbl] "
    exit -1
fi

cobc -x -o sandbox sandbox.cbl

if [ $? -ne "0" ]
then
    echo "TRACER: error [sandbox.cbl] "
    exit -1
fi

./sandbox

