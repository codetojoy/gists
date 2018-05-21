
### Notes for Jenkins job

* build parameterized
    * Git Parameter plugin
* SCM Git
    * repo for `gists`
* Build Env
    * inject env vars with properties file path: `./easy_war_may_2018/setvars.properties`

### Notes for building from tag

* [1] Git parameter plugin
    * will pull the list of tags
    * will set an env var such as `MY_GIT_TAG`
        * confirmed with tracer script, and appears to be passed to Gradle via `-D`:w

* [2] source-code management / Git section
    * branches to build can use tags
    * see https://stackoverflow.com/a/19632917/12704
    * e.g. in Branches to build, build specifier `refs/tags/tag_E_20_MAY_2018`
    * *unification of [1] and [2]:
        * e.g. in Branches to build, build specifier `refs/tags/${MY_GIT_TAG}`

### Questions

* can we build with no tag ? i.e. mix master and tags?
