#!/bin/bash 

[ -z "$1" ] && echo "requires tag argument" && exit 1;
[ -z "$2" ] && echo "requires ENV" && exit 1;

TAG=$1
ENV=$2

git tag --list -n80 $TAG | grep "${ENV}.artifact.url" | awk -F'"' '{print $4}' 
 
