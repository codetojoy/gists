
package net.codetojoy.util;

public class Util {
    public String foo(String s) {
        return "foo :: " + s;
    }

    public String protectedBar(String s) {
        new CallLock().lock("net.codetojoy.example");
        return "bar :: " + s;
    }
}

