#!/bin/bash

# xargs ./cleaner.sh < tmp.clean.txt 
xargs -0 -n 1 ./cleaner.sh < <(tr \\n \\0 <tmp.clean.txt)
