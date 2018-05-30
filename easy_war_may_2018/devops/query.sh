#!/bin/bash

QUERY_FILE=$1

ARTIFACTORY_SERVER=localhost:8081
curl -u admin:password -X POST http://${ARTIFACTORY_SERVER}/artifactory/api/search/aql -d @$QUERY_FILE
