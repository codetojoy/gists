
package net.codetojoy;

public class FooDAO {

    public Foo createFoo(int id, Bar bar) {
        Foo foo = new Foo(id, bar);

        // mock DB call
        try { Thread.sleep(10*1000); } catch (Exception ex) {} 

        return foo;
    }
}
