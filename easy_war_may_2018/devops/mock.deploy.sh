#!/bin/bash 

[ -z "$1" ] && echo "requires tag argument" && exit 1;
[ -z "$2" ] && echo "requires ENV" && exit 1;
[ -z "$3" ] && echo "requires out file" && exit 1;

TAG=$1
ENV=$2
ARTIFACT_URL_FILE=$3

wget `cat $ARTIFACT_URL_FILE`
echo "TRACER wget result $?"

ls -l *.war  
