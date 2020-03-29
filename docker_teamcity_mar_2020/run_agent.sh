#!/bin/bash 

MY_HOME=/Users/measter/src/github/codetojoy/gists/docker_teamcity_mar_2020
mkdir -p $MY_HOME/data_agent

SERVER_NAME=teamcity-server-instance

docker run -it -e SERVER_URL="http://$SERVER_NAME:8111"  \
    -v $MY_HOME/data_agent:/data/teamcity_agent/conf  \
    --link $SERVER_NAME \
    jetbrains/teamcity-agent

