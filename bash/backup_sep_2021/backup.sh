#!/bin/bash

set -e 

if [ -z "$1" ]
  then
    echo "check usage"
    exit -1
fi

VERSION=$1
SRC_HTML=index.html
SRC_JSON=data.json
TARGET_HTML=index.v$VERSION.html
TARGET_JSON=data.v$VERSION.json

if [ ! -f $TARGET_HTML ]
then
    cp $SRC_HTML $TARGET_HTML
else
    echo "$TARGET_HTML already exists!"
    exit -1
fi

if [ ! -f $TARGET_JSON ]
then
    cp $SRC_JSON $TARGET_JSON
else
    echo "$TARGET_JSON already exists!"
    exit -1
fi
