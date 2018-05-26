
package net.codetojoy;

public class Runner {
    public static void main(String[] args) {
        AsyncPool pool = new AsyncPool();

        FooService fooService = new FooService();
        fooService.initialize(pool);
        fooService.invokeBarService();
        pool.shutdown();

        System.out.println("Ready");
    }
}
