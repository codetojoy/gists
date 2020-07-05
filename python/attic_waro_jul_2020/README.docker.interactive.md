
### Summary

* instructions for running inside Docker container
* setup:
    - `docker pull python:3.8.3`
    - `docker create -it --name waro-python -v $(pwd):/tmp python:3.8.3`
* to run app:
    - `docker start waro-python`
    - `docker exec -w /tmp -it waro-python bash`
    - `./run.sh`
* to execute tests:
    - `docker start waro-python`
    - `docker exec -w /tmp -it waro-python bash`
    - `pip install -U pytest`
        - TODO: how to use `pipenv` to remove this step ??
        - Presumably, if installed `pipenv` in the container, then we 
          wouldn't have to install `pytest` (or any package) explicitly.
    - `./test.sh`
