#!/bin/bash

# this shows a build in Artifactory with 2 artifacts

./gradlew clean war publish artifactoryPublish 
