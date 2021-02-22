#!/bin/bash

set -e

if [ -z "$PROJECT_NAME" ]
then
    echo "define PROJECT_NAME with setvars.sh"
    exit -1
fi

# Hello World 

dotnet new console
dotnet new sln 
mkdir src 
mv *.cs ./src/.
mv *.csproj ./src/.
dotnet sln add src/$PROJECT_NAME.csproj 

# Unit Test

mkdir tests
cd tests
dotnet new nunit
dotnet add reference ../src/$PROJECT_NAME.csproj
cd ..
dotnet sln add ./tests/tests.csproj

# Copy files
cp ./resources/Foo.cs ./src/.
cp ./resources/FooTests.cs ./tests/.

echo "Ready."
