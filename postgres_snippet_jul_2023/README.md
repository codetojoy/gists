
### SQL

* key query: select *  from plan where payload @> '[{"isFoo":true}]'


### Summary

* see [here](https://stackoverflow.com/a/4070385/12704) on Stack O

* `./go_setup.sh` will clean, build, populate 
* `./usecase.sh` runs the use case of concern
    * `doSeed` determines if the plan is created or assumed to exist
* `./selector.sh` will query a table (as CLI argument)
* `./reader.sh` will query various tables

### pg admin

* `./run_pgadmin.sh` (contains pull)
* http://localhost in browser`
    - u: user@domain.com, p: swordfish
    - can use `docker inspect` for this as well 
* MUST create a new connection to db
* to find IP address: 
    * use `./inspect.sh my-pg` with name of pg docker container
    - this will give the IP that can be used for pgadmin
        - IP from above
        - u: postgres, p: swordfish

### Notes

* TODO: de-duplicate
* TODO: write as stored procedure

