#!/bin/bash

export JOB_BUILD_HOME=~/.jenkins/jobs/Easy_Too_May_2018/workspace/easy_war_may_2018/build/libs

echo "viewing ..." ; sleep 1
cat $JOB_BUILD_HOME/../../src/main/webapp/jsp/index.jsp 

echo "Ready."
