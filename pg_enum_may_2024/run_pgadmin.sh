#!/bin/bash

# docker pull dpage/pgadmin4:7.2

docker run -p 80:80 \
    -e 'PGADMIN_DEFAULT_EMAIL=user@domain.com' \
    -e 'PGADMIN_DEFAULT_PASSWORD=swordfish' \
    -d dpage/pgadmin4:7.2
