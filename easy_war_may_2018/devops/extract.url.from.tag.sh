#!/bin/bash 

[ -z "$1" ] && echo "requires tag argument" && exit 1;
[ -z "$2" ] && echo "requires ENV" && exit 1;
[ -z "$3" ] && echo "requires out file" && exit 1;

TAG=$1
ENV=$2
ARTIFACT_URL_FILE=$3

if [ -f $ARTIfACT_URL_FILE ]; then
    echo ""
else
    rm $ARTIFACT_URL_FILE
fi

git tag --list -n80 $TAG | grep "${ENV}.artifact.url" | awk -F'"' '{print $4}' > $ARTIFACT_URL_FILE
 
