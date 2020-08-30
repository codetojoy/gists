#!/bin/bash

go test -test.v

if [ "$?" -eq "0" ] 
then
    echo ""
    echo "TRACER OK"
    echo ""
fi
