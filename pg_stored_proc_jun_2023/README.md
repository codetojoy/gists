
### Summary

* set up is from ~/gists/postgres_jun_2023 
    - `./go_setup.sh`
    - `./usecase.sh` with doSeed = true and early exit
* see `proc.sql` for s2 function
    - create via pgAdmin or SQL client
    - it just does a count on the table `bridge_reference_plan`
* `./caller.sh` to run function via JDBC (in Groovy)  

