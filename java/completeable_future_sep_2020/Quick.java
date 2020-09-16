 
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

class Task implements Supplier<String> {
    private static int instanceCount = 0;

    Task() {
        instanceCount++;
    }

    int getRandom() {
        final int min = 1;
        final int max = 5;
        int result = ThreadLocalRandom.current().nextInt(min, max + 1);
        return result;
    }

    @Override
    public String get() {
        String whoAmI = "TRACER " + instanceCount;

        try {
            int delayInSeconds = getRandom();
            System.out.println(whoAmI + " sleeping (" + delayInSeconds + " s)"); 
            TimeUnit.SECONDS.sleep(delayInSeconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        return whoAmI + " done";
    }
}
 
public class Quick {
    void goSingle() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Task());

        String result = future.get();
        System.out.println(result);
    }

    // from https://www.callicoder.com/java-8-completablefuture-tutorial/

    List<String> goMany(List<Supplier<String>> tasks) throws Exception {
        List<CompletableFuture<String>> futures = new ArrayList<>();

        for (var task : tasks) {
            var f = CompletableFuture.supplyAsync(task);
            futures.add(f);
        }

        var futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        var allFutures = CompletableFuture.allOf(futuresArray);

        // CompletableFuture<List<String>>
        var compoundFuture = allFutures.thenApply(v -> {
            return futures.stream()
                             .map(f -> f.join())
                             .collect(Collectors.toList());
        });

        var results = compoundFuture.get();
        return results;
    }

    public static void main(String[] args) throws Exception {
        var quick = new Quick();

        int which = 2;

        if (which == 1) {
            quick.goSingle();
        } else if (which == 2) {
            var tasks = new ArrayList<Supplier<String>>();
            tasks.add(new Task());
            tasks.add(new Task());
            tasks.add(new Task());

            var results = quick.goMany(tasks);

            for (var result : results) {
                System.out.println("TRACER result: " + result);
            }
        }
        System.out.println("Ready.");
    }
}
