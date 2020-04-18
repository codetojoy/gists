
### Summary

* This is not a serious venture!
* With the demand for COBOL during the pandemic, I thought it would be interesting to "kick the tires" a bit. (I took one semester of COBOL, centuries ago).

### Starting Docker

* using [1], download image from docker: `docker pull gregcoleman/docker-cobol`
* edit folder in `run.sh` as appropriate 
* to use image: `run.sh`

### Run example 1

* at prompt in image: `cd /data`
* at prompt in image: `./run_first.sh`

### Run example 2

* at prompt in image: `cd /data`
* at prompt in image: `./run_main.sh`

### Re-starting Docker

* `docker start cobol-instance`
* `docker exec -it cobol-instance bash`


### Resources

* [1] - https://hub.docker.com/r/gregcoleman/docker-cobol/
* [2] - https://medium.com/@yvanscher/7-cobol-examples-with-explanations-ae1784b4d576
