#!/bin/bash

docker run --name test2-ibm-was -h test \
-v $(pwd)/PASSWORD:/tmp/PASSWORD \
-v $(pwd)/data:/tmp/data \
-p 9043:9043 -p 9443:9443 -d ibmcom/websphere-traditional:latest
