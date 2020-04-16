#!/bin/bash 

MY_HOME=/Users/measter/src/github/codetojoy/gists/COBOL/sandbox

docker run -it --name cobol-instance  \
    -v $MY_HOME/src:/data \
    gregcoleman/docker-cobol
