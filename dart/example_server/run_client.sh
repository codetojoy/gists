#!/bin/bash

echo ""
echo "--------------"
echo "get ..."
curl -X GET "http://localhost:4040"

echo ""

echo "--------------"
echo "post ..."
curl -d "id=5150&name=mozart" -X POST http://localhost:4040 
echo ""

echo "--------------"
