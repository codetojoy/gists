
package net.codetojoy 

import groovy.json.*

import org.apache.http.client.methods.*
import org.apache.http.entity.*
import org.apache.http.impl.client.*

class RESTClient {
    static final String url = 'http://jsonplaceholder.typicode.com/posts'

    String doPostToExternalService() {
        // build JSON

        def map = [:]
        map['name'] = 'Wolfgang Mozart'
        map['address'] = 'Vienna'
        map['handle'] = 'wolfgang1756'

        def jsonBody = new JsonBuilder(map).toString()

        // build HTTP POST

        def post = new HttpPost(url)

        post.addHeader('content-type', 'application/json')
        post.setEntity(new StringEntity(jsonBody))

        // execute 

        def client = HttpClientBuilder.create().build()
        def response = client.execute(post)

        def bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))
        def jsonResponse = bufferedReader.getText()

        return jsonResponse

        // def slurper = new JsonSlurper()
        // def resultMap = slurper.parseText(jsonResponse)

        // assert "Maritime DevCon" == resultMap["name"]
        // assert resultMap["id"] != null
    }
}
