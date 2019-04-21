
package net.codetojoy;

import net.codetojoy.db.DatabaseInfo; 

public class App {
    public void go() {
        try (DatabaseInfo databaseInfo = new DatabaseInfo()) {
            throw new IllegalStateException("test exception");
        } catch (IllegalStateException ex) {
            System.out.println("TRACER catch block");
        } finally {
            System.out.println("TRACER finally block");
        }
    }

    public static void main(String[] args) {
        new App().go();
        System.out.println("Ready.");
    }
}
