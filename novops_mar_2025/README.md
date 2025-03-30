
## Summary

* Novops [here](https://novops.dev/)
* GitHub [here](https://github.com/PierreBeucher/novops)

## Prerequisites

* assume these are in AWS SSM on localdev
    * `name "my-api-key", value "evh-5150"`
    * `name "/net/codetojoy/server-id", value "mozart-1756"`
* for Bash, requires new version of Bash via HomeBrew
	* macOS is frozen at 3.2.5 and this example won't work

## Define Novops config file

* in working folder, define: `.novops.yml`
* NOTE: observe that this is safe to put in git:

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

## load Novops 

* step 0: set AWS SSM env vars for access
* step 1: in Bash, run `source <(novops load)` 
* example:

```
$ . ../docker/localstack/setvars.sh 
$ source <(novops load)
$ env | egrep "API|SERVER"

MY_API_KEY=evh-5150
MY_SERVER_ID=mozart-1756
```
