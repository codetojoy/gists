#!/bin/bash

set -e 

cd tests
dotnet test -v d
cd ..
