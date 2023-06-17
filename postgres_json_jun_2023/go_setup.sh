#!/bin/bash

set -e

echo "cleaning ..."
groovy SQL_Clean.groovy 

echo "building ..."
groovy SQL_Client.groovy 

echo "populating ..."
groovy SQL_Populate.groovy 

echo "Ready."

