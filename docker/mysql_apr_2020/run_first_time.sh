#!/bin/bash

docker run --name sandbox2-mysql -p 3306:3306 \
-e MYSQL_ROOT_PASSWORD=root5150 \
-e MYSQL_USER=mozart \
-e MYSQL_PASSWORD=1756 \
-e MYSQL_DATABASE=foo \
-d mysql:5.7
