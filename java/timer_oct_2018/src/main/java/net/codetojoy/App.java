package net.codetojoy;

public class App {
    public void caseA() {
        Timer timer = new Timer();
        new Ping().ping();
        System.out.println(timer.getElapsed("TRACER case A "));
    }

    public void caseB() {
        Timer timer = new Timer();
        new Ping().doWait(62 * 1000L);
        System.out.println(timer.getElapsed("TRACER case B "));
    }

    public static void main(String... args) {
        App app = new App();

        for (int i = 0; i <= 5; i++) {
            app.caseA();
        }

        System.out.println("TRACER beginning long wait ...");
        System.out.println("");
        app.caseB();
        System.out.println("TRACER Ready.");
    }
}
