#!/bin/bash

docker pull python:3.8.3

docker run \
    --rm --name waro-python \
    -i --log-driver=none -a stdin -a stdout -a stderr -w /tmp \
    -v $(pwd):/tmp \
    python:3.8.3 \
    python3 waro/main.py config.json
