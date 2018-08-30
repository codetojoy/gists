
package net.codetojoy;

public class Ping {
    public long ping() {
        long result = System.currentTimeMillis();
        long delay = result % 2000;
        try { Thread.sleep(delay); } catch (Exception ex) {} 
        return result;
    }
}
