#!/bin/bash

set -e 

./gradlew test | tee test.out.log 
