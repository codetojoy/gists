#!/bin/bash

cd $1

git add devops/DEV.tag.artifact.map.csv

git commit -m "automated commit for tag artifact map"
git push origin master 

