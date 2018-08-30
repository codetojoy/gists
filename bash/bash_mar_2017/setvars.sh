#!/bin/bash

filename="setvars.properties"

while read -r line
do
if [[ $line =~ ^.*#.*$ ]]; then
    # comment: no-op
    dummy=5150
elif [[ $line =~ ^[[:space:]]*$ ]]; then
    # spaces: no-op
    dummy=5150
elif [[ $line =~ ^.*=.*$ ]]; then
    echo "export $line"
else
    echo "ERROR: $line"
    exit -1
fi
done < "$filename" > setvars_tmp.sh 

chmod 777 setvars_tmp.sh

