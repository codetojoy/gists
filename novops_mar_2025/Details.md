
## Summary

* Novops [here](https://novops.dev/)
* GitHub [here](https://github.com/PierreBeucher/novops)

## Bash

* requires newer version of Bash
* see [this page](https://www.shell-tips.com/mac/upgrade-bash/#gsc.tab=0) 

## Prerequisites

* load values into AWS SSM on localdev

```
#!/bin/bash

aws --no-sign-request ssm put-parameter \
    --name "my-api-key" \
    --value "evh-5150" \
	--overwrite \
    --type String 

aws --no-sign-request ssm put-parameter \
    --name "/net/codetojoy/server-id" \
    --value "mozart-1756" \
	--overwrite \
    --type String 
```

## define Novops config file

* in working folder, define: `.novops.yml`
* this is safe to put in git:

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

* assumes AWS SSM env vars have been set
* e.g. in Bash, run `source <(novops load)` 
* example:

```
$ . ../docker/localstack/setvars.sh 
$ source <(novops load)
$ env | egrep "API|SERVER"

MY_API_KEY=evh-5150
MY_SERVER_ID=mozart-1756
```
