

### command workflow

* setup:

```
brew install adr-tools
export EDITOR=vim
```

* the following creates `doc/adr/0001-record-architecture-decisions.md`

```
adr init
```

* the following creates `doc/adr/0002-implement-restful-api.md`

```
adr new implement RESTful API 
```

* the following creates decision `test 1` which is superceded by `test 2`
    * assume `test 1` is `0003-test-1-decision.md`
* both ADRs will reflect the supercede status

```
adr new test 1 decision 
adr list
adr new -s 3 test 2 decision
```

### Resources

[1] - https://github.com/npryce/adr-tools

