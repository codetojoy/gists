
### Create

* `createTag2` requires hash as command-line property (see `create.tag.v2.sh`)
* we no longer use `getLatestURL` but find the URL by matching the hash

### Deploy

* see new `devops/Deploy.Jenkinsile`
* Jenkins job requires input for (a) tag (b) ENV
* `devops/extract.url.from.tag.sh` gets the Artifactory URL 
* `devops/mock.deploy.sh` downloads the WAR file via Artifactory URL 

### TODO

* write embedded tests
* write a constant section 
* can we trigger Jenkins on Github events? as a test
* consider docker names per build
* improve the Node setup 
* remove duplication of `sh` and `aql` in `devops` and `PomSeacher`
* X - handle the Git commit race-condition
* X - confirm that CSV files are no longer needed 
