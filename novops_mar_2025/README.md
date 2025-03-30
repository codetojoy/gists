
## Summary

* Novops [here](https://novops.dev/)
* GitHub [here](https://github.com/PierreBeucher/novops)

## Prerequisites

* assume these are in AWS SSM on localdev
    * `name "my-api-key", value "evh-5150"`
    * `name "/net/codetojoy/server-id", value "mozart-1756"`
* for Bash, requires new version of Bash via HomeBrew
	* macOS is frozen at 3.2.5 and this example won't work
* Define script that requires `MY_API_KEY`, `MY_SERVER_ID`

```
#!/bin/bash

set -e 

if [[ -z "${MY_API_KEY}" ]]; then
	echo "MY_API_KEY is not set"
	exit -1
fi

if [[ -z "${MY_SERVER_ID}" ]]; then
	echo "MY_SERVER_ID is not set"
	exit -1
fi

echo "did work using env vars: OK"
```

## Define Novops config file

* in working folder, define: `.novops.yml`
	* maps AWS SSM parameter to env var
* Crucial point: this is safe to put in git!

```
environments:
  dev:
    variables:
    - name: MY_API_KEY
      value:
        aws_ssm_parameter:
          name: my-api-key
    - name: MY_SERVER_ID
      value:
        aws_ssm_parameter:
          name: /net/codetojoy/server-id
```

## Run script with Novops 

* step 0: set AWS SSM env vars for access
* step 1: in Bash, `novops run -- ./do_something.sh`
* example:

```
$ . ../docker/localstack/setvars.sh 
$ novops run -- ./do_something_with_env_vars.sh
$
$ TRACER-do-something: using MY_API_KEY : evh-5150
$ TRACER-do-something: using MY_SERVER_ID : mozart-1756
$ TRACER-do-something: OK 
```
