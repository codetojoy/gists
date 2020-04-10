
from: https://docs.microsoft.com/en-us/dotnet/core/testing/unit-testing-with-nunit

* step: 
    - let ROOT_DIR=waro.sandbox.C#
    - `mkdir $ROOT_DIR`
* in ROOT_DIR
    - `dotnet new sln`
* make and cd to: `$ROOT_DIR/PrimeService` 
    - `dotnet new classlib`
    - `vim PrimeService.cs`
* in ROOT_DIR
    - `dotnet sln add PrimeService/PrimeService.csproj`
* make and cd to: `$ROOT_DIR/PrimeService.Tests` 
    - `dotnet new nunit`
    - `dotnet add reference ../PrimeService/PrimeService.csproj`
* in ROOT_DIR
    - `dotnet sln add ./PrimeService.Tests/PrimeService.Tests.csproj`
* in: `ROOT_DIR/PrimeService.Tests` 
    - `vim PrimeService_IsPrimeShould.cs`
* in ROOT_DIR
    - `dotnet test`
