
### Intro

* Simple example using Google Guava's Cache
* Consider an app with items and users
    * We want to detect if an item id is being edited by other users
* The cache contains a `UserEditKey` which indicates intent for user A to edit item X

### To Run

* `./run.sh` will start a command-line app 
* `cmd: [E=edit, F=finalize, L=list, V=view, Q=quit]`
