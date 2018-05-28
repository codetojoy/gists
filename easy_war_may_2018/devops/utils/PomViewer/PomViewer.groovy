
@Grab('org.apache.httpcomponents:httpclient:4.5.5')

import org.apache.http.client.methods.*
import org.apache.http.impl.client.*
import org.apache.http.entity.*

def getPOM = { def METADATA_URL ->
    def buildInfo = ""

    def metadataRequest = new HttpGet(METADATA_URL)
    def client = HttpClientBuilder.create().build()
    def response = client.execute(metadataRequest)

    def bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))
    def result = bufferedReader.getText()

    return result
}

// main ------------

try {
    if (! args || args.length < 1) { println "usage: groovy PomViewer.groovy URL" }
    def pomURL =  args[0] 
    def pomXML = getPOM(pomURL)
    println pomXML
    println "Ready."
} catch (Exception ex) {
    System.err.println "TRACER caught exception: " + ex.message
}

