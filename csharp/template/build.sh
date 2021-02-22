#!/bin/bash

set -e

if [ -z "$PROJECT_NAME" ]
then
    echo "define PROJECT_NAME with setvars.sh"
    exit -1
fi

dotnet new console
dotnet new sln 
mkdir src 
mv *.cs ./src/.
mv *.csproj ./src/.
dotnet sln add src/$PROJECT_NAME.csproj 

echo "Ready."
