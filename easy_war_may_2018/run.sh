#!/bin/bash

export TOMCAT_HOME=~/tools/tomcat_7062_on_5150

$TOMCAT_HOME/bin/shutdown.sh

gradle clean build

cp build/libs/easytoo-1.0.0-SNAPSHOT.war /Users/measter/tools/tomcat_7062_on_5150/webapps/easytoo.war

rm -rf $MY_TOOLS_HOME/tomcat_7062_on_5150/webapps/easytoo

$TOMCAT_HOME/bin/startup.sh

echo "Ready."
