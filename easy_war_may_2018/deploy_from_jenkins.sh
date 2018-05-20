#!/bin/bash

export TOMCAT_HOME=~/tools/tomcat_7062_on_5150

$TOMCAT_HOME/bin/shutdown.sh

sleep 3

export JOB_BUILD_HOME=~/.jenkins/jobs/Easy_Too_May_2018/workspace/easy_war_may_2018/build/libs

cp $JOB_BUILD_HOME/easytoo-1.0.0-SNAPSHOT.war /Users/measter/tools/tomcat_7062_on_5150/webapps/easytoo.war

rm -rf $MY_TOOLS_HOME/tomcat_7062_on_5150/webapps/easytoo

$TOMCAT_HOME/bin/startup.sh

sleep 3

cat $TOMCAT_HOME/webapps/easytoo/jsp/index.jsp 

echo "Ready."
