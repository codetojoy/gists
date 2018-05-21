#!/bin/bash

export JOB_BUILD_HOME=~/.jenkins/jobs/Easy_Too_May_2018/workspace/easy_war_may_2018/build/libs
export TMP_HOME=./tmp

echo "setting up ..." ; sleep 1
mkdir -p $TMP_HOME
rm -rf $TMP_HOME
mkdir $TMP_HOME

echo "extracting ..." ; sleep 1
cd $TMP_HOME
jar xvf $JOB_BUILD_HOME/easytoo-1.0.1-SNAPSHOT.war 
cd - 

echo "viewing ..." ; sleep 1
cat $TMP_HOME/jsp/index.jsp 

echo "Ready."
