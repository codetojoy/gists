#!/bin/bash 

[ -z "$1" ] && echo "requires tag argument" && exit 1;
[ -z "$2" ] && echo "requires message file" && exit 1;

TAG=$1
TAG_MESSAGE_FILE=$2

git tag $TAG -a -F $TAG_MESSAGE_FILE
git push origin $TAG

