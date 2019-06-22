
package net.codetojoy;

import net.codetojoy.client.Client;

public class App {
    public static void main(String[] args) {
        Client client = new Client();
        client.inputLoop();
        System.out.println("Ready.");
    }
}
