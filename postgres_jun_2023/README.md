
### Summary

* see [here](https://stackoverflow.com/a/4070385/12704) on Stack O

* `./go_setup.sh` will clean, build, populate 
* `./usecase.sh` runs the use case of concern
    * `doSeed` determines if the plan is created or assumed to exist
* `./selector.sh` will query a table (as CLI argument)
* `./reader.sh` will query various tables

### pg admin

* `./run_pgadmin.sh` (contains pull)
* use `./inspect.sh` with name of pg docker container
    - this will give the IP that can be used for pgadmin

### Notes

* TODO: de-duplicate
* TODO: write as stored procedure

