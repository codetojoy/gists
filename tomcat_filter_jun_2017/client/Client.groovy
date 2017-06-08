 
@Grab(group='org.apache.httpcomponents', module='httpclient', version='4.5.2')

import groovy.json.*

import org.apache.http.client.methods.*
import org.apache.http.entity.*
import org.apache.http.impl.client.*

def url = 'http://127.0.0.1:5150/easyweb?client=groovy'

def emitInfo = { prefix, client, response ->
    def sessionCookie = client.getCookieStore()?.cookies[0]
    assert sessionCookie != null 

    println "${prefix} sessionCookie: " + sessionCookie

    println "${prefix} response size: " + new BufferedReader(new InputStreamReader(response.getEntity().getContent())).getText().size()
}

// ------------ main

// call 1
def httpGet = new HttpGet(url)
def client = new DefaultHttpClient()
def response = client.execute(httpGet)

emitInfo("TRACER 1", client, response)

// call 2 with same client (and now session cookie)
httpGet = new HttpGet(url)
response = client.execute(httpGet)

emitInfo("TRACER 2", client, response)

// call 3 with same client (and now session cookie)
httpGet = new HttpGet(url)
response = client.execute(httpGet)

emitInfo("TRACER 3", client, response)

