package net.codetojoy.strategy;

import net.codetojoy.Constants;
import net.codetojoy.http.Web;

import java.net.URI;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.URIBuilder;

public class PingRemote {
    private final String scheme;
    private final String host;
    private final String path;
    private boolean hasChecked = false;
    private boolean cachedPingValue = false;

    public PingRemote(String scheme, String host, String path) {
        this.scheme = scheme;
        this.host = host;
        this.path = path;
    }

    public boolean ping() {
        // guard
        if (hasChecked) {
            return cachedPingValue;
        }
        hasChecked = true;
        cachedPingValue = false;

        try {
            var uri = buildPingURI();
            var result = new Web().get(uri);
            var pingResult = buildPingResult(result);
            var message = pingResult.getMessage();
            if (message != null && (! message.trim().isEmpty())) {
                cachedPingValue = true;
            }
        } catch (Exception ex) {
            System.err.println("error on ping: " + ex.getMessage());
        }
        return cachedPingValue;
    }

    private URI buildPingURI() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)
               .setHost(host)
               .setPath(path)
               .build();

        return uri;
    }

    protected PingResult buildPingResult(String str) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        PingResult result = mapper.readValue(str, PingResult.class);
        return result;
    }
}

class PingResult {
    private String message;

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String toString() {
        return " message: " + message;
    }
}
