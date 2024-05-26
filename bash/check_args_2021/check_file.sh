#!/bin/bash

stat bogus.check_file.sh > /dev/null 2>&1

if [ $? -eq "0" ]; then
  echo "found file"
else
  echo "could NOT find file"
fi

echo "Ready."

