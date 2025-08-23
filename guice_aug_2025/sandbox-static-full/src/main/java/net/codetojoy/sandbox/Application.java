
package net.codetojoy.sandbox;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.logging.Logger;
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

        dbService = injector.getInstance(DBService.class);
        userService = injector.getInstance(UserService.class);

        if (!dbService.isInitialized()) {
            throw new IllegalStateException("dbService not initialized !?");
        }

        if (!userService.isInitialized()) {
            throw new IllegalStateException("userService not initialized !?");
        }
    }

    // -------------------------- main 

    private void run() {
        logger.info(buildLog("userService: " + userService.getUser("user-mozart")));
        logger.info(buildLog("dbService: " + dbService.query("5150")));
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
