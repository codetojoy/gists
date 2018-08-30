
package net.codetojoy;

public class Runner {
    public static void main(String[] args) {
        FooService fooService = new FooService();
        fooService.initialize();
        fooService.invokeBarService();
        System.out.println("Ready");
    }
}
