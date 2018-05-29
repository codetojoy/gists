#!/bin/bash 

[ -z "$TRUNK_MAJOR_VERSION" ] && echo "please use setvars.sh" && exit 1;
[ -z "$TRUNK_MINOR_VERSION" ] && echo "please use setvars.sh" && exit 1;
[ -z "$TRUNK_INCREMENTAL_VERSION" ] && echo "please use setvars.sh" && exit 1;
[ -z "$TRUNK_QUALIFIER" ] && echo "please use setvars.sh" && exit 1;

MAJOR=$TRUNK_MAJOR_VERSION
MINOR=$TRUNK_MINOR_VERSION
INCREMENTAL=$TRUNK_INCREMENTAL_VERSION
QUALIFIER=$TRUNK_QUALIFIER
TIMESTAMP=`date '+%Y-%m-%d-%H%M'`
TAG=v${MAJOR}.${MINOR}.${INCREMENTAL}.1

echo "TRACER tag: ${TAG}"

# TODO: confirm argument

git tag $TAG -a -m " \
date: ${TIMESTAMP} \
artifact: easywar ${MAJOR}-${MINOR}-${INCREMENTAL}-${QUALIFIER} \
QA_URL: http://example.qa.com \
UAT_URL: http://example.uat.com \
name: drunken-hoover \
"

# git push origin $TAG
