#!/bin/bash 

cobc -x -o main1 main1.cbl
cobc -o util util.cbl
./main1

