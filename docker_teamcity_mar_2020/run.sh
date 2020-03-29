#!/bin/bash 

MY_HOME=/Users/measter/src/github/codetojoy/gists/docker_teamcity_mar_2020
mkdir -p $MY_HOME/data

docker run -it --name teamcity-server-instance  \
    -v $MY_HOME/data:/data/teamcity_server/datadir \
    -v $MY_HOME/data:/opt/teamcity/logs  \
    -p 8111:8111 \
    jetbrains/teamcity-server
