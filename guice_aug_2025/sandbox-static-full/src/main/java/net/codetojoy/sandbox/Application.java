
package net.codetojoy.sandbox;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

import static net.codetojoy.sandbox.util.MyLog.buildLog;

public class Application {
    private DBService dbService;
    private UserService userService;

    // -------------------------- logging 
    private static final Logger logger = Logger.getLogger(Application.class.getName());

    private void configureLogging() {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.INFO);
    }

    // -------------------------- init 
        
    private void init() {
        Injector injector = Guice.createInjector(
            new InfrastructureModule(),
            new BusinessLogicModule()
        );

        // Verify BusinessLogicModule validation
        String validation = injector.getInstance(Key.get(String.class, Names.named("module.business.validation")));
        logger.info(buildLog("BusinessLogicModule validation result: " + validation));

        dbService = injector.getInstance(DBService.class);
        userService = injector.getInstance(UserService.class);

        if (!dbService.isInitialized()) {
            throw new IllegalStateException("dbService not initialized !?");
        }

        if (!userService.isInitialized()) {
            throw new IllegalStateException("userService not initialized !?");
        }
    }

    // -------------------------- worker threads 

    private String getUser(String id) {
        String result = userService.getUser(id);
        logger.info(buildLog("userService: " + result));
        return result;
    }
        
    private String doQuery(String id) {
        String result = dbService.query(id);
        logger.info(buildLog("dbService: " + result));
        return result;
    }

    private void doWork() { 
        logger.info(buildLog("starting worker threads..."));
        List<CompletableFuture> futures = new ArrayList<>();
        int numWorkers = 6;

        for (int i = 0; i < numWorkers; i++) {
            final boolean isEven = (i % 2 == 0);
            final String dbId = "id-" + i;
            final String userId = "user-" + i; 

            futures.add(CompletableFuture.supplyAsync(() -> 
                (isEven) ? doQuery(dbId) : getUser(userId)
            ));
        }

        futures.forEach(CompletableFuture::join);
    }

    // -------------------------- main 

    private void run() {
        doWork();
        getUser("user-mozart");
        doQuery("5150");
    }

    public static void main(String[] args) {
        try {
            Application app = new Application();

            app.configureLogging();
            app.init();
            app.run();

            logger.info(buildLog("Ready."));
        } catch (Exception e) {
            logger.info(buildLog("caught exception: " + e.getMessage()));
        }
    }
}
