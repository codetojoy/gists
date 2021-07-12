#!/bin/bash

PORT=4040

echo ""

echo "--------------"
echo "get v1 ..."
curl -X GET "http://localhost:$PORT/v1?id=6160&name=chopin"
echo ""


echo "--------------"
echo "get v2/foo ..."
curl -X GET "http://localhost:$PORT/v2/foo?id=6160&name=chopin"
echo ""

echo "--------------"
echo "get v2/bar ..."
# headers to stdout:
# curl -D - -X GET "http://localhost:$PORT/v2/bar?id=7170"
curl -X GET "http://localhost:$PORT/v2/bar?id=7170" | jq
echo ""

echo "--------------"
echo "post ..."
curl -d "id=5150&name=mozart" -X POST http://localhost:$PORT 
echo ""

echo "--------------"
