
package net.codetojoy.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.*;

// http://localhost:8080/waro/strategy?prize_card=10&max_card=12&mode=max&cards=4&cards=6&cards=2" 

class Result {
    private int card;
    private String message;

    public int getCard() { return card; }
    public void setCard(int card) { this.card = card; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String toString() {
        return "card: " + card + " message: " + message;
    }
}

public class Runner {
    private static final String BASE_URL = "http://localhost:8080/waro/strategy";
    private static final int MAX_CARD = 12;
    private static final int PRIZE_CARD = 10;
    private static final String MODE = "max";

    private URI buildURI(List<Integer> cards) throws Exception {
        URIBuilder builder = new URIBuilder();

        builder.setScheme("http")
               .setHost("localhost:8080")
               .setPath("/waro/strategy")
               .setParameter("mode", MODE)
               .setParameter("prize_card", "" + PRIZE_CARD)
               .setParameter("max_card", "" + MAX_CARD);

        
        for (var card : cards) {
            builder.setParameter("cards", "" + card);
        }

        URI uri = builder.build();

        return uri;
    }

    private Result buildResult(String str) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Result result = mapper.readValue(str, Result.class);
        return result;
    }

    public void run() throws Exception {
        var cards = new ArrayList<Integer>(List.of(4,6,2));        
        var uri = buildURI(cards);

        HttpGet request = new HttpGet(uri);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().getStatusCode()); 

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity);
                Result result = buildResult(resultStr);
                System.out.println("TRACER: " + result);
            }
        }
    }

    public static void main(String... args) {
        var runner = new Runner();
        
        try {
            runner.run();
        } catch (Exception ex) {
            System.err.println("TRACER caught ex: " + ex.getMessage());
        }   

        System.out.println("Ready.");
    }
}

// ------

/*
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {

            HttpGet request = new HttpGet("https://httpbin.org/get");

            // add request headers
            request.addHeader("custom-key", "mkyong");
            request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

            CloseableHttpResponse response = httpClient.execute(request);

            try {

                // Get HttpResponse Status
                System.out.println(response.getProtocolVersion());              // HTTP/1.1
                System.out.println(response.getStatusLine().getStatusCode());   // 200
                System.out.println(response.getStatusLine().getReasonPhrase()); // OK
                System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                }

            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }

    }

}
*/
