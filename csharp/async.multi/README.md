

### Summary

* attempt to understand how to wait on multiple concurrent tasks 
* 26-FEB-2021
    - simple async call to WarO_Strategy_API_Java (with 4 sec delay) works OK
    - other async examples are messy 

Dependency:
---------

* will call `WarO_Strategy_API_Java` as the API server

Origin:
---------

* edit `setvars.sh` to set `PROJECT_NAME`
* edit `*.cs` in `resources` to set the namespace
* `. ./setvars.sh`
* `./build.sh`
* `./run.sh`
* `./test.sh`
