
package net.codetojoy.component;

import net.codetojoy.utils.Utils;

import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;

import java.io.*;

// This class has some basic fields / methods so that the published jar can be tested.
// The code itself is not interesting. 

public class WebComponent {
    public static final String TEST_URL = "http://jsonplaceholder.typicode.com/posts";
    public static final String TEST_JSON = "{\"name\":\"Mozart\",\"address\":\"Vienna\"}";

    public String doHTTPPost(String url, String jsonBody) {
        String result = "";

        try {
            HttpPost post = new HttpPost(url);
            post.addHeader("content-type", "application/json");
            post.setEntity(new StringEntity(jsonBody));

            CloseableHttpClient client = HttpClientBuilder.create().build();
            CloseableHttpResponse response = client.execute(post);

            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            result = new Utils().readBuffer(br);
        } catch (Exception ex) {
            result = ex.getMessage();
            System.err.println("caught exception: " + ex);
        }

        return result;
    }
}

