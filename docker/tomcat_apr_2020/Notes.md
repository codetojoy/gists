
### Notes

* this assumes the sandbox WAR project is built
* also assumes that MySQL container is running 
* these steps prove that containers in "Docker for Mac" can see each
  other by using `host.docker.internal` as domain 

### Steps

* `docker pull tomcat`
* `./run.sh` 
* put WAR file in $PWD/data 
* `docker exec -it naughty_mccarthy bash`
* cp WAR file to /usr/local/tomcat/webapps
* `docker restart naughty_mccarthy`
* browse: http://localhost:7777/sandbox/simple.jsp 
* assuming MySQL container is running:
    - browse: http://localhost:7777/sandbox/index.jsp 
    - note that jdbc URL uses `host.docker.internal`

### Resources

* [1] - Dockerfile - https://hub.docker.com/_/tomcat
