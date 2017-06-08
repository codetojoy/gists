
MY_TOMCAT_HOME=/Users/measter/tools/tomcat_7062_on_5150

gradle clean jar

$MY_TOMCAT_HOME/bin/shutdown.sh

rm -f $MY_TOMCAT_HOME/logs/*
rm -f $MY_TOMCAT_HOME/lib/my_filter.jar
cp build/libs/filter.jar $MY_TOMCAT_HOME/lib/my_filter.jar

$MY_TOMCAT_HOME/bin/startup.sh

echo "Ready."
