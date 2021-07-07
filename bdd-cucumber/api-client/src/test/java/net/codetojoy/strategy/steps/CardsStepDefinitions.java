package net.codetojoy.strategy.steps;

import net.codetojoy.Constants;
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

    private final Integers integers = new Integers();
    private final Lists lists = new Lists();

    @Given("prizeCard: {int} maxCard: {int} cards: {string}")
    public void givenInitialInput(int prizeCard,
                                  int maxCard,
                                  String cardsStr) {
        this.prizeCard = prizeCard;
        this.maxCard = maxCard;
        this.cards = lists.parseList(cardsStr);
    }

    @When("mode {string}")
    public void modeType(String mode) {
        this.mode = mode;
    }

    @Then("card selection should be {int}")
    public void cardSelectionShouldBe(int expected) {
        var hand = cards.stream().mapToInt(Integer::intValue);
        var scheme = Constants.SCHEME;
        var host = Constants.HOST;
        var path = Constants.PATH;
        var apiRemote = new ApiRemote(scheme, host, path, mode);
        var actual = apiRemote.selectCard(prizeCard, hand, maxCard);
        assertEquals((int) expected, (int) actual);
    }
}
