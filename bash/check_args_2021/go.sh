#!/bin/bash

set -e 

if [ -n "$1" ]; then
MY_VALUE=$1
else
  echo "usage: param1"
  exit -1
fi

echo "received ${MY_VALUE}"
