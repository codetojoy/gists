#!/bin/bash 

TAG=$1

# TODO: confirm argument

git tag $TAG
git push origin $TAG
