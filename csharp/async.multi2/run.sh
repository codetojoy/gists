#!/bin/bash

set -e 

. ./setvars.sh 

if [ -z "$PROJECT_NAME" ]
then
    echo "define PROJECT_NAME with setvars.sh"
    exit -1
fi

dotnet run -p src/$PROJECT_NAME.csproj
