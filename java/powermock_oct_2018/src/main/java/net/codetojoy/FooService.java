
package net.codetojoy;

public class FooService {

    // return Foo or possibly Bar
    public Foo getFoo(int id) {
        Foo result = null;

        if (id % 2 == 0) {
            FooDAO.getFoo(id);
        } else {
            FooDAO.getBar(id);
        }

        return result;
    } 
}
