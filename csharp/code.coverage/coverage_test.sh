#!/bin/bash

set -e 

cd tests

dotnet test /p:CollectCoverage=true /p:CoverletOutputFormat=cobertura 

dotnet reportgenerator -reports:coverage.cobertura.xml -targetdir:Coverage_Report -reportTypes:htmlInline

cd ..

echo "Browse to : ./Coverage_Report/index.htm"
