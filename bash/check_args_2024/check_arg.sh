#!/bin/bash

set -e 

if [ -n "$1" ]; then
MY_VALUE=$1
else
  echo "usage: param1"
  exit -1
fi

echo "received ${MY_VALUE}"

if [ $MY_VALUE -eq "5150" ]; then
    echo "value matches secret"
else 
    echo "value present but incorrect"
fi

echo "Ready."
