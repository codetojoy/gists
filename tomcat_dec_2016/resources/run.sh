#!/bin/bash

curl --user tomcat:tomcat 'http://127.0.0.1:5150/manager/text/list'

curl --user tomcat:tomcat 'http://127.0.0.1:5150/manager/text/stop?path=/greeting'
curl --user tomcat:tomcat 'http://127.0.0.1:5150/manager/text/undeploy?path=/greeting'
curl --user tomcat:tomcat 'http://127.0.0.1:5150/manager/text/deploy?path=/greeting&war=file:/Users/measter/tmp/08.DEC/greeting.war'

echo "Ready."
