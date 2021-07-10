package net.codetojoy.strategy;

import net.codetojoy.Constants;
import net.codetojoy.http.Web;

import java.util.stream.*;
import java.io.IOException;
import java.net.URI;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.URIBuilder;

public final class ApiRemote implements Strategy {
    private final String scheme;
    private final String host;
    private final String path;
    private final String mode;

    public ApiRemote(String scheme, String host, String path, String mode) {
        this.scheme = scheme;
        this.host = host;
        this.path = path;
        this.mode = mode;
    }

    @Override
    public int selectCard(int prizeCard, IntStream hand, int maxCard) {
        var bid = 0;

        try {
             bid = apiRemoteSelectCard(prizeCard, hand, maxCard);
        } catch (Exception ex) {
            System.err.println("ERROR caught exception: " + ex.getMessage());
            // bail out for now
            System.exit(-1);
        }

        return bid;
    }

    private int apiRemoteSelectCard(int prizeCard, IntStream hand, int maxCard) throws Exception {
        var uri = buildURI(prizeCard, hand, maxCard);
        var result = new Web().get(uri);
        var apiResult = buildResult(result);
        var card = apiResult.getCard();

        return card;
    }

    private URI buildURI(int prizeCard, IntStream hand, int maxCard) throws Exception {
        URIBuilder builder = new URIBuilder();

        builder.setScheme(scheme)
               .setHost(host)
               .setPath(path)
               .setParameter(Constants.MODE_PARAM, mode)
               .setParameter(Constants.PRIZE_CARD_PARAM, "" + prizeCard)
               .setParameter(Constants.MAX_CARD_PARAM, "" + maxCard);

        var cardsStrings = hand.boxed()
                               .map(c -> "" + c)
                               .collect(Collectors.toList());

        var cardsQueryValue = String.join(",", cardsStrings);

        builder.setParameter(Constants.CARDS_PARAM, cardsQueryValue);

        URI uri = builder.build();

        return uri;
    }

    protected ApiResult buildResult(String str) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ApiResult result = mapper.readValue(str, ApiResult.class);
        return result;
    }
}

class ApiResult {
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
