
### Notes: Server

* from: https://hub.docker.com/r/jetbrains/teamcity-server/
* set `MY_HOME` in `run.sh`
* config settings are stored in `~/data/teamcity_server/datadir`

* install: 

```
docker pull jetbrains/teamcity-server
./run_it.sh
```

* browse to http://localhost:8111
* TC will walk through license and creating admin account
* see Build Agent below 
* `ctrl-C` will shutdown
* to restart: `docker start teamcity-server-instance`

### Notes: Build Agent

* from: https://hub.docker.com/r/jetbrains/teamcity-agent/
* set `MY_HOME` in `run_agent.sh`
* set `SERVER_NAME` in `run_agent.sh`
    - from: https://stackoverflow.com/a/41919227/12704
* config settings are stored in `~/data_agent`

* install:

```
docker pull jetbrains/teamcity-agent
./run_agent.sh 
```

* go to TC UI and authorize the Build Agent 
