
package net.codetojoy;

public class Foo {
    private final int id;
    private final Bar bar;

    public Foo(int id, Bar bar) {
        this.id = id;
        this.bar = bar;
    }

    public int getId() { return id; }
    public Bar getBar() { return bar; }
}
