
package net.codetojoy.usecase;

public class Runner {
    public static void main(String[] args) throws Exception {
        var usecase = new Usecase();
        usecase.apply();
        System.out.println("Ready.");
    }
}

