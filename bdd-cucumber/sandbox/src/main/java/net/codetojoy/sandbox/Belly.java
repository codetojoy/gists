package net.codetojoy.sandbox;

public class Belly {
    private int cukes = 0;
    private int waitTime = 0;

    public void eat(int cukes) {
        this.cukes = cukes;
    }

    public void wait(int hours) {
        this.waitTime = hours;
    }

    public String getState() {
        String state = "undefined";
        int maxCukes = 50;
        if (cukes < maxCukes && waitTime > 0) {
            state = "growl";
        }
        return state;
    }
}
