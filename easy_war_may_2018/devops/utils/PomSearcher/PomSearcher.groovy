
@Grab('org.apache.httpcomponents:httpclient:4.5.5')

import org.apache.http.client.methods.*
import org.apache.http.impl.client.*
import org.apache.http.entity.*

import groovy.json.JsonSlurper
import groovy.util.XmlSlurper

def ARTIFACTORY_SERVER = "http://localhost:8081/artifactory"

def httpGet = { def url ->
    def request = new HttpGet(url)
    def client = HttpClientBuilder.create().build()
    def response = client.execute(request)

    def bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))
    def result = bufferedReader.getText()
    return result
}

def callBashScript = { command ->
    def sysout = new StringBuilder()
    def syserr = new StringBuilder()
    def process = command.execute()
    process.consumeProcessOutput(sysout, syserr)
    process.waitForOrKill(7000)
    if (process.exitValue() != 0) { 
        println "ERROR: cmd: ${command} error: ${syserr}"
    }
    assert 0 == process.exitValue()
    return sysout.toString()
}

def getBuildInfoForArtifact = { def artifactURL ->
    def buildInfo = null

    def pomURL = artifactURL.replaceAll("war", "pom")
    def pomXML = httpGet(pomURL)
    def xml = new XmlSlurper().parseText(pomXML)
    def jsonStr = xml.properties."foo-gitCommitInfo".text()

    if (jsonStr ==~ /\{"commit":.*/) {
        buildInfo = new JsonSlurper().parseText(jsonStr)
    }

    return buildInfo
}

def findArtifactForCommitHash = { targetCommitHash ->
    def queryResult = callBashScript "./query.sh q1.aql"
    def queryJson = new JsonSlurper().parseText(queryResult)
    queryJson["results"].each { result ->
        def repo = result["repo"]
        def path = result["path"]
        def name = result["name"]
        def artifactURL = "${ARTIFACTORY_SERVER}/${repo}/${path}/${name}"
        def buildInfo = getBuildInfoForArtifact(artifactURL)
        if (buildInfo) {
            def thisCommitHash = buildInfo["commit"]
            if (thisCommitHash ==~ "${targetCommitHash}.*") {
                println "artifactURL : " + artifactURL
                println "commit : " + thisCommitHash
                println "target : " + targetCommitHash
                println ""
            }
        }
    }
}

// main ------------

try {
    if (! args || args.length < 1) { println "usage: groovy PomSearcher.groovy commitHash" }
    def targetCommitHash = args[0] 
    findArtifactForCommitHash(targetCommitHash)
    println "Ready."
} catch (Exception ex) {
    System.err.println "TRACER caught exception: " + ex.message
}

