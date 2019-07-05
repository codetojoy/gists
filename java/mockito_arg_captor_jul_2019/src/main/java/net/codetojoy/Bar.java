
package net.codetojoy;

public class Bar {
    protected final static String PREFIX = "bar::";

    private final int id;
    private final String name;

    public Bar(int id) {
        this.id = id;
        this.name = PREFIX + id;
    }

    public int getId() { return id; }
    public String getName() { return name; }
}
