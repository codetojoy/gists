
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

