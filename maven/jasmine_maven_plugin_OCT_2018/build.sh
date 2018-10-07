#!/bin/bash

mvn archetype:generate \
 -DarchetypeGroupId=com.github.searls \
 -DarchetypeArtifactId=jasmine-archetype \
 -DarchetypeVersion=RELEASE \
 -DjasminePluginVersion=2.2 \
 -DgroupId=com.acme \
 -DartifactId=my-jasmine-project \
 -Dversion=0.0.1-SNAPSHOT
