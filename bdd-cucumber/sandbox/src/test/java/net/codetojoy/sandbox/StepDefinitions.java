package net.codetojoy.sandbox;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class StepDefinitions {
    private Belly belly = new Belly();

    @Given("I have {int} cukes in my belly")
    public void I_have_cukes_in_my_belly(int cukes) {
        belly.eat(cukes);
    }

    @When("I wait {int} hour")
    public void I_wait(int durationInHours) {
        belly.wait(durationInHours);
    }

    @Then("my belly should {string}")
    public void My_Belly_Is(String expectedState) {
        String state = belly.getState();
        assertEquals(expectedState, state);
    }
}
