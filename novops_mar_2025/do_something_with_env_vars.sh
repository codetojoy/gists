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

echo "did work with env vars: OK"
