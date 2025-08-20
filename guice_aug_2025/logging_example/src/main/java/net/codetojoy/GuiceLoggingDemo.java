package net.codetojoy;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

// Service interface
interface MessageService {
    String getMessage();
}

// Service implementation
@Singleton
class MessageServiceImpl implements MessageService {
    private static final Logger logger = Logger.getLogger(MessageServiceImpl.class.getName());
    
    @Inject
    public MessageServiceImpl() {
        // logger.finest("MessageServiceImpl constructor called");
    }
    
    @Override
    public String getMessage() {
        // logger.finest("getMessage() method called");
        return "Hello from Guice!";
    }
}

// Consumer class that uses the service
@Singleton
class MessageConsumer {
    private static final Logger logger = Logger.getLogger(MessageConsumer.class.getName());
    private final MessageService messageService;
    
    @Inject
    public MessageConsumer(MessageService messageService) {
        // logger.finest("MessageConsumer constructor called with MessageService injection");
        this.messageService = messageService;
    }
    
    public void displayMessage() {
        // logger.finest("displayMessage() method called");
        String message = messageService.getMessage();
        System.out.println("Message: " + message);
        // logger.finest("Message displayed successfully");
    }
}

// Guice module configuration
class AppModule extends AbstractModule {
    private static final Logger logger = Logger.getLogger(AppModule.class.getName());
    
    @Override
    protected void configure() {
        // logger.finest("Configuring Guice module");
        bind(MessageService.class).to(MessageServiceImpl.class);
        // logger.finest("MessageService bound to MessageServiceImpl");
    }
}

// Main application class
public class GuiceLoggingDemo {
    private static final Logger logger = Logger.getLogger(GuiceLoggingDemo.class.getName());
    
    public static void main(String[] args) {
        // Configure logging to show FINEST level messages
        configureLogging();
        
        // logger.finest("Application starting");
        
        // Create Guice injector
        // logger.finest("Creating Guice injector");
        Injector injector = Guice.createInjector(new AppModule());
        // logger.finest("Guice injector created successfully");
        
        // Get instance and use it
        // logger.finest("Getting MessageConsumer instance");
        MessageConsumer consumer = injector.getInstance(MessageConsumer.class);
        // logger.finest("MessageConsumer instance obtained");
        
        // Use the consumer
        consumer.displayMessage();
        
        // logger.finest("Application completed");
    }
    
    private static void configureLogging() {
        // Get the root logger
        Logger rootLogger = Logger.getLogger("");
        
        // Set level to FINEST for all loggers
        rootLogger.setLevel(Level.FINEST);
        
        // Configure Guice-specific loggers
        Logger.getLogger("com.google.inject").setLevel(Level.FINEST);
        Logger.getLogger("com.google.inject.internal").setLevel(Level.FINEST);
        
        // Remove default handlers
        for (var handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }
        
        // Create and configure console handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.FINEST);
        consoleHandler.setFormatter(new SimpleFormatter());
        
        // Add handler to root logger
        rootLogger.addHandler(consoleHandler);
        
        System.out.println("=== FINEST Level Logging Configured ===");
        System.out.println("This will show detailed Guice internal operations");
        System.out.println("==========================================\n");
    }
}
