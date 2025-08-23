
package net.codetojoy.sandbox;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.logging.Logger;
import java.util.logging.Level;

public class MinimalRunner {
    private DBService dbService;

    // -------------------------- logging 
    private static final Logger logger = Logger.getLogger(MinimalRunner.class.getName());

    private void configureLogging() {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.INFO);
    }

    // -------------------------- init 
        
    private void init() {
        Injector injector = Guice.createInjector(
            new InfrastructureModule()
        );

        dbService = injector.getInstance(DBService.class);

        if (!dbService.isInitialized()) {
            throw new IllegalStateException("dbService not initialized !?");
        }

        logger.info("TRACER init OK");
    }

    // -------------------------- main 

    private void run() {
        logger.info("TRACER run: dbService says: " + dbService.query("5150"));
    }

    public static void main(String[] args) {
        try {
            MinimalRunner runner = new MinimalRunner();

            runner.configureLogging();
            runner.init();
            runner.run();

            logger.info("TRACER v 0.09.09 Ready.");
        } catch (Exception e) {
            logger.info("TRACER caught exception: " + e.getMessage());
        }
    }
}
