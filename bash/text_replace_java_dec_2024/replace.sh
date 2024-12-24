#!/bin/bash

TARGET_FILE=$1

# see https://stackoverflow.com/a/79153150/12704

sed -i '' "s/import akka/import org.apache.pekko/g" $TARGET_FILE
sed -i '' "s/AKKA/PEKKO/g" $TARGET_FILE
sed -i '' "s/Akka/Pekko/g" $TARGET_FILE

