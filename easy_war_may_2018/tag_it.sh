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
TAG=easywar-${MAJOR}-${MINOR}-${INCREMENTAL}-${QUALIFIER}-${TIMESTAMP}

echo "TRACER tag: ${TAG}"

# TODO: confirm argument

git tag $TAG
git push origin $TAG
