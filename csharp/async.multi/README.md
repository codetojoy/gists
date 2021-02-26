

### Summary

* attempt to understand how to wait on multiple concurrent tasks 
* 26-FEB-2021
    - simple async call to WarO_Strategy_API_Java (with 4 sec delay) works OK
        - now in `async.single`
* token test here

Dependency:
---------

* will call `WarO_Strategy_API_Java` as the API server

Steps:
---------

* in `src` : `dotnet add package Newtonsoft.Json --version 12.0.3`

Origin:
---------

* see `../template`
