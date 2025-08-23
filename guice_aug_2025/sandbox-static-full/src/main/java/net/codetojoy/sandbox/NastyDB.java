
package net.codetojoy.sandbox;

import static net.codetojoy.sandbox.util.Constants.LOG_FORMAT;

public class NastyDB {
    private static boolean initialized = false;

    public static String query(String id) {
        confirmInit();
        String status = "{\"id\": \"" + id + "\"}";
        return String.format(LOG_FORMAT, "NastyDB", status); 
    }

    public static boolean isInitialized() {
        confirmInit();
        return initialized;
    }
    
    // ------------ private init
    
    private synchronized static void confirmInit() {
        if (!initialized) {
            init();
        }
    }

    private static void init() {
        System.out.println("TRACER NastyDB init");
        try {
            System.out.println("TRACER NastyDB pathogenic dely on init...");
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
        }
        initialized = true;
    }
}

