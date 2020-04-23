#!/bin/bash

docker run -it --rm -p 7777:8080 -v $(pwd)/data:/data tomcat:7.0.103-jdk8-openjdk
