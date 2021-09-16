#!/bin/bash

curl -X GET http://localhost:8000/files/index.html
echo "status: $?"
echo "Ready."
