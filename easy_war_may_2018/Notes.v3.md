
### Create

* `createTag2` requires hash as command-line property (see `create.tag.v2.sh`)
* we no longer use `getLatestURL` but find the URL by matching the hash

### Deploy

* see new `devops/Deploy.Jenkinsile`
* Jenkins job requires input for (a) tag (b) ENV
* `devops/extract.url.from.tag.sh` gets the Artifactory URL (writes to file)
* `devops/mock.deploy.sh` downloads the WAR file via Artifactory URL 

### TODO

* remove duplication of `sh` and `aql` in `devops` and `PomSeacher`
* confirm that CSV files are no longer needed 
