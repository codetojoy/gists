
Origin:
---------

* `dotnet new console`
* `dotnet new sln`
* article [here](https://insimpleterms.blog/adding-nunit-tests-to-a-net-core-console-app)
    - `mkdir src`
    - move code and `.csproj` to `./src/.`
    - `dotnet sln add src/code.coverage.csproj`
    - `dotnet run -p src/code.coverage.csproj`
    - tests
        - `cd tests`
        - `dotnet new nunit`
        - `dotnet add reference ../src/code.coverage.csproj`
        - `cd ..`
        - `dotnet sln add ./tests/tests.csproj`

Coverage:
---------

* doc [here](https://github.com/coverlet-coverage/coverlet)
* project [here](https://github.com/coverlet-coverage/coverlet)
* in `tests`, `dotnet add package coverlet.msbuild` 
* see [2] below to manually add report generator to tests.csproj
* see `coverage_test.sh`
* report generator:
    - [1 Stack O](https://stackoverflow.com/questions/51911135/how-to-see-a-friendly-report-of-unit-tests-using-net-core-and-coverlet)
    - [2 here](https://medium.com/bluekiri/code-coverage-in-vsts-with-xunit-coverlet-and-reportgenerator-be2a64cd9c2f)
