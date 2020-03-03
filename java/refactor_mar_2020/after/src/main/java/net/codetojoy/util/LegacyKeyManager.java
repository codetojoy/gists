
package net.codetojoy.util;

import java.util.Date;

public class LegacyKeyManager {
    private static boolean isInit = false;

    private static void nastyInit() {
        if (! isInit) {
            try {
                System.out.println("TRACER simulating call to database");
                Thread.sleep(10_000L);
                isInit = true;
            } catch(Exception ex) {
            }
        }
    }

    public static String getValue(String key) {
        nastyInit();
        String result = new Date().toString();
        return result;
    }
}
