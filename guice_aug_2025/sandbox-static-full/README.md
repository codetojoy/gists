
### Summary

* `BusinessLogicModule`
    * depends on `InfrastructureModule`
    * contains `UserService`
* `InfrastructureModule` has `DBService`
    * contains `DBServiceImpl` 
        * uses `NastyDB` which has static methods
        * has pathogenic delay on startup
* `Application` configures modules and exercises services 

