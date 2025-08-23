
package net.codetojoy.sandbox;

public class NastyDB {
    private static boolean initialized = false;

    public static String query(String id) {
        confirmInit();
        return "{\"who\":\"NastyDB\", \"status\":\"OK\", \"id\": \"" + id + "\"}";
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
        initialized = true;
    }
}

