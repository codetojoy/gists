#!/bin/bash 

PROJECT_DIR=$1
PROJECT_PREFIX=susan
TOMCAT_HOME=/Users/measter/tools/tomcat_8_5_32_5151

mvn clean test package

wget http://localhost:5151 -o tmp.log -O tmp.html

if [ $? == 0 ]; then
    echo "shutting down"
    $TOMCAT_HOME/bin/shutdown.sh
else
    echo "Tomcat not running"
fi 

rm tmp.log tmp.html 

rm -rf $TOMCAT_HOME/webapps/${PROJECT_PREFIX}*
cp ./target/$PROJECT_PREFIX.war $TOMCAT_HOME/webapps

echo "starting up"
$TOMCAT_HOME/bin/startup.sh

echo ""
echo ""
echo "------------------"
echo "browse to: http://localhost:5151/$PROJECT_PREFIX/index.jsp" 
echo "Ready."
