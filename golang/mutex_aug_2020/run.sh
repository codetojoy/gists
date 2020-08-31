#!/bin/bash

go run quick.go > out.log 2>&1
RESULT=$?

echo "result: ${RESULT}"
echo "Ready."
