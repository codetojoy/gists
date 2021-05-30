#!/bin/bash

set -e

# check arg
if [ -n "$1" ]; then
TARGET=$1

# check target exists
if [ -d $TARGET ]; then
# stat $TARGET > /dev/null 2>&1
echo "OK for target: $TARGET"
fi

else
  echo "usage: param1"
  exit -1
fi

