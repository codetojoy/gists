#!/bin/bash

cd $1

git add devops/tag.artifact.mapping.csv
git commit -m "automated commit for tag.artifact.mapping.csv"
git push origin master 

