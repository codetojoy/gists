#!/bin/bash 

cobc -x -o main1 main_util.cbl
cobc -o util util.cbl
./main1

