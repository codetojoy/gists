#!/bin/bash

set -e 

if [[ -z "${MY_API_KEY}" ]]; then
	echo "MY_API_KEY is not set"
	exit -1
fi

if [[ -z "${MY_SERVER_ID}" ]]; then
	echo "MY_SERVER_ID is not set"
	exit -1
fi

echo "TRACER-do-something: MY_API_ID : ${MY_API_ID}"
echo "TRACER-do-something: MY_SERVER_ID : ${MY_SERVER_ID}"
echo "TRACER-do-something: OK"
