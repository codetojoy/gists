#!/bin/bash

set -e  

rm -rf target
mvn dependency:copy-dependencies
ls -lrt target/dependency

echo "Ready."
