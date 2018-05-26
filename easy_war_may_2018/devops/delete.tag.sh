#!/bin/bash 

[ -z "$1" ] && echo "requires tag argument" && exit 1;

TAG=$1

git tag -d $TAG
git push origin :refs/tags/$TAG
