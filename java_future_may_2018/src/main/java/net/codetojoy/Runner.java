
package net.codetojoy;

public class Runner {
    public static void main(String[] args) throws Exception {
        FooService fooService = new FooService();
        fooService.initialize();
        fooService.invokeBarService();
        System.out.println("Ready");
    }
}
