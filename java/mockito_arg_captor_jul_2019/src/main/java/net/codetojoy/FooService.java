
package net.codetojoy;

public class FooService {
    private FooDAO fooDAO = null;

    public void setFooDAO(FooDAO fooDAO) {
        this.fooDAO = fooDAO;
    }

    public Foo getFoo(int id) {
        Bar bar = new Bar(id); 

        Foo result =  fooDAO.createFoo(id, bar);

        return result;
    } 
}
