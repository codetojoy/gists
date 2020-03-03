
package net.codetojoy.client;

import net.codetojoy.util.*;


public class Runner {
    private static void runAsBefore() {
        // a major goal is that this class does not change from 'before' to 'after' 
        System.out.println("TRACER running...");
        String value = KeyManager.getValue("randomKey");
        System.out.println("TRACER value: " + value);
    }

    private static void runAsMock() {
        System.out.println("TRACER mock setup");
        KeyManagerApi mockKeyManager = new KeyManagerApi() {
            @Override 
            public String getValue(String key) { return "mock value!"; }
        };
        KeyManager.setKeyManagerApi(mockKeyManager);
        runAsBefore();
    }

    public static void main(String[] args) {
        if (args[0].equalsIgnoreCase("before")) {
            runAsBefore();
        } else {
            runAsMock();
        }
    }
}
