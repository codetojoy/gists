package net.codetojoy.strategy.steps;

import static net.codetojoy.Constants.*;
import net.codetojoy.strategy.utils.*;
import net.codetojoy.strategy.*;

import io.cucumber.java.en.*;

import java.time.*;
import java.util.*;

import static org.junit.Assert.*;

public class CardsStepDefinitions {
    private Integer prizeCard = null;
    private Integer maxCard = null;
    private String mode = null;
    private List<Integer> cards = null;
    private Strategy apiRemote;
    private int actual;

    private final Integers integers = new Integers();
    private final Lists lists = new Lists();

    @Given("prizeCard: {int} maxCard: {int} cards: {string} mode: {string}")
    public void givenInitialInput(int prizeCard,
                                  int maxCard,
                                  String cardsStr,
                                  String mode) {
        // TODO: find a better home for this:
        var pingRemote = new PingRemote(SCHEME, HOST, PING_PATH);
        if (!pingRemote.ping()) {
           throw new IllegalStateException("server not running");
        }
        this.prizeCard = prizeCard;
        this.maxCard = maxCard;
        this.cards = lists.parseList(cardsStr);
        this.mode = mode;
    }

    @When("I select card")
    public void iSelectCard() {
        var hand = cards.stream().mapToInt(Integer::intValue);
        var apiRemote = new ApiRemote(SCHEME, HOST, PATH, mode);
        actual = apiRemote.selectCard(prizeCard, hand, maxCard);
        // System.out.println("TRACER calling API a: " + actual);
    }

    @Then("card selection should be {int}")
    public void cardSelectionShouldBe(int expected) {
        assertEquals((int) expected, (int) actual);
    }
}
