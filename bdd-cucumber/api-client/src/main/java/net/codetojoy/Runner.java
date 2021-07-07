
package net.codetojoy;

import net.codetojoy.strategy.*;

import java.util.*;
// import java.util.stream.*;

public class Runner {
    void run() {
        var scheme = Constants.SCHEME;
        var host = Constants.HOST;
        var path = Constants.PATH;
        var mode = Constants.MODE;

        var prizeCard = 7;
        var cards = List.of(2,4,6,8,10);
        var hand = cards.stream().mapToInt(Integer::intValue);
        var maxCard = 18;

        var strategy = new ApiRemote(scheme, host, path, mode);
        var card = strategy.selectCard(prizeCard, hand, maxCard);
        System.out.println("TRACER card: " + card);
    }

    public static void main(String[] args) {
        new Runner().run();
        System.out.println("Ready.");
    }
}
