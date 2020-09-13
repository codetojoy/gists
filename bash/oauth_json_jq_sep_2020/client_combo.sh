#!/bin/bash

set -e 

### step 1
curl -u orderprocessingapp:orderprocessingappsecret \
-H "Content-Type: application/json" \
-d @grant.type.json \
http://localhost:8085/oauth/token > oauth_response.json

cat oauth_response.json | jq '.access_token' > access_token.txt
rm oauth_response.json

ACCESS_TOKEN_STR=`cat access_token.txt`
ACCESS_TOKEN="${ACCESS_TOKEN_STR%\"}"
ACCESS_TOKEN="${ACCESS_TOKEN#\"}"
rm access_token.txt

### step 2

curl  -v http://localhost:8080/orders \
-H 'Content-Type: application/json' \
-H "Authorization: Bearer ${ACCESS_TOKEN}" \
-d @data.json | jq

