#!/bin/bash

docker run --name test-ibm-was -h test -v $(pwd)/PASSWORD:/tmp/PASSWORD \
  -p 9043:9043 -p 9443:9443 -d ibmcom/websphere-traditional:latest
