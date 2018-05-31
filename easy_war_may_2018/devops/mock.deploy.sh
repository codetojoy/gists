#!/bin/bash 

[ -z "$1" ] && echo "requires tag argument" && exit 1;
[ -z "$2" ] && echo "requires ENV" && exit 1;
[ -z "$3" ] && echo "requires URL" && exit 1;

TAG=$1
ENV=$2
ARTIFACT_URL=$3

wget $ARTIFACT_URL

echo "TRACER v2:  wget result $?"

ls -l *.war  
