
### Steps for Admin Console

* Assumes Docker Desktop [1] is installed
* Per instructions at [2], open command-line terminal and pull image:
    - NOTE: this is a massive download 
    - `docker pull ibmcom/websphere-traditional`
* create a `PASSWORD` file in this directory which will contain the password for `wsadmin`
* at command-line: `./run_first_time.sh` 
    - wait 2+ minutes
    - browse to Admin console [3] and login with `wsadmin` and password in `PASSWORD`

### Test deployment

* at command-line, `cd sandbox_project`
* build ear file:
    - Bash, `./gradlew :earwrapper:ear`
    - Windows, `./gradlew.bat :earwrapper:ear` 
    - EAR file is located at `~/sandbox_project/build/libs/sandbox.ear`
* Go to Admin Console [3]
    - click Application at left
    - click New Application
    - upload EAR file located at `~/sandbox_project/build/libs/sandbox.ear`
* Browse to app at [4] 

### Misc

* after `./run_first_time.sh` is used, use `stop.sh` and `start.sh`

### Notes

[1] - Docker Desktop [here](https://www.docker.com/products/docker-desktop)
[2] - IBM Dockerfile [here](https://hub.docker.com/r/ibmcom/websphere-traditional)
[3] - Admin console [here]( https://localhost:9043/ibm/console/login.do?action=secure)
[4] - Sandbox JSP [here](https://localhost:9443/sandbox/)
