
package net.codetojoy.client;

import net.codetojoy.util.KeyManager;

// a major goal is that this class does not change from 'before' to 'after' 

public class Runner {
    public static void main(String[] args) {
        System.out.println("TRACER running...");
        String value = KeyManager.getValue("randomKey");
        System.out.println("TRACER value: " + value);
    }
}
