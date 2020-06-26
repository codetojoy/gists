#!/bin/bash

for i in {1..30}
do
   echo "..."
done

./test.sh

if [ "$?" -eq "0" ]
then
  ./run.sh 
fi 
