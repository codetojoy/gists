#!/bin/bash 

MY_HOME=/Users/measter/src/github/codetojoy/gists/COBOL/waro

docker run -it --name cobol-waro-instance  \
    -v $MY_HOME/src:/data \
    gregcoleman/docker-cobol
