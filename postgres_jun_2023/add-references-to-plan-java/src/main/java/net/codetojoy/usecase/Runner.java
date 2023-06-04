
package net.codetojoy.usecase;

public class Runner {
    public static void main(String[] args) {
        try {
            System.out.println("v2");
            var usecase = new Usecase();
            usecase.apply();
            System.out.println("Ready.");
        } catch (Exception ex) {
            System.err.println("ex: " + ex.getMessage());
        }
    }
}

