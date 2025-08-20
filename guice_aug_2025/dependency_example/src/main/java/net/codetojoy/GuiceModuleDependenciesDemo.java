package net.codetojoy;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.Properties;

// ===== MODULE A SERVICES (Foundation/Infrastructure) =====

// Configuration service that other services depend on
interface ConfigurationService {
    String getProperty(String key);
    int getIntProperty(String key);
    boolean isInitialized();
}

@Singleton
class ConfigurationServiceImpl implements ConfigurationService {
    private static final Logger logger = Logger.getLogger(ConfigurationServiceImpl.class.getName());
    private final Properties properties;
    private volatile boolean initialized = false;
    
    @Inject
    public ConfigurationServiceImpl() {
        logger.info("🔧 ConfigurationService constructor - Foundation service being created");
        System.out.println("🔧 Creating ConfigurationService (Module A foundation)");
        
        // Simulate loading configuration
        this.properties = new Properties();
        properties.setProperty("database.url", "jdbc:h2:mem:testdb");
        properties.setProperty("database.pool.size", "10");
        properties.setProperty("cache.enabled", "true");
        properties.setProperty("cache.ttl", "300");
        
        this.initialized = true;
        System.out.println("✅ ConfigurationService initialized with properties");
    }
    
    @Override
    public String getProperty(String key) {
        if (!initialized) {
            throw new IllegalStateException("ConfigurationService not initialized!");
        }
        return properties.getProperty(key);
    }
    
    @Override
    public int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }
    
    @Override
    public boolean isInitialized() {
        return initialized;
    }
}

// Database connection service (depends on configuration)
interface DatabaseConnectionService {
    void connect();
    boolean isConnected();
    String getConnectionInfo();
}

@Singleton
class DatabaseConnectionServiceImpl implements DatabaseConnectionService {
    private static final Logger logger = Logger.getLogger(DatabaseConnectionServiceImpl.class.getName());
    private final ConfigurationService configService;
    private boolean connected = false;
    private String connectionInfo;
    
    @Inject
    public DatabaseConnectionServiceImpl(ConfigurationService configService) {
        logger.info("🗄️ DatabaseConnectionService constructor - Depends on ConfigurationService");
        System.out.println("🗄️ Creating DatabaseConnectionService (depends on Module A)");
        
        if (!configService.isInitialized()) {
            throw new IllegalStateException("ConfigurationService must be initialized first!");
        }
        
        this.configService = configService;
        initializeConnection();
    }
    
    private void initializeConnection() {
        String url = configService.getProperty("database.url");
        int poolSize = configService.getIntProperty("database.pool.size");
        
        this.connectionInfo = String.format("Connected to %s with pool size %d", url, poolSize);
        this.connected = true;
        
        System.out.println("✅ Database connection established: " + connectionInfo);
    }
    
    @Override
    public void connect() {
        if (!connected) {
            initializeConnection();
        }
        logger.info("Database connection verified");
    }
    
    @Override
    public boolean isConnected() {
        return connected;
    }
    
    @Override
    public String getConnectionInfo() {
        return connectionInfo;
    }
}

// ===== MODULE B SERVICES (Business Logic - depends on Module A) =====

// Cache service that depends on both configuration and database
interface CacheService {
    void put(String key, String value);
    String get(String key);
    boolean isEnabled();
}

@Singleton
class CacheServiceImpl implements CacheService {
    private static final Logger logger = Logger.getLogger(CacheServiceImpl.class.getName());
    private final ConfigurationService configService;
    private final DatabaseConnectionService dbService;
    private final java.util.Map<String, String> cache = new java.util.HashMap<>();
    private final boolean enabled;
    private final int ttl;
    
    @Inject
    public CacheServiceImpl(ConfigurationService configService, DatabaseConnectionService dbService) {
        logger.info("💾 CacheService constructor - Depends on Module A services");
        System.out.println("💾 Creating CacheService (Module B - depends on Module A services)");
        
        // Verify dependencies are properly initialized
        if (!configService.isInitialized()) {
            throw new IllegalStateException("ConfigurationService must be initialized first!");
        }
        if (!dbService.isConnected()) {
            throw new IllegalStateException("DatabaseConnectionService must be connected first!");
        }
        
        this.configService = configService;
        this.dbService = dbService;
        this.enabled = Boolean.parseBoolean(configService.getProperty("cache.enabled"));
        this.ttl = configService.getIntProperty("cache.ttl");
        
        System.out.println("✅ CacheService initialized - enabled: " + enabled + ", TTL: " + ttl);
    }
    
    @Override
    public void put(String key, String value) {
        if (enabled) {
            cache.put(key, value);
            logger.finest("Cached value for key: " + key);
        }
    }
    
    @Override
    public String get(String key) {
        return enabled ? cache.get(key) : null;
    }
    
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

// Business service that uses all the infrastructure
interface UserBusinessService {
    void createUser(String username);
    String getUserInfo(String username);
}

@Singleton
class UserBusinessServiceImpl implements UserBusinessService {
    private static final Logger logger = Logger.getLogger(UserBusinessServiceImpl.class.getName());
    private final DatabaseConnectionService dbService;
    private final CacheService cacheService;
    
    @Inject
    public UserBusinessServiceImpl(DatabaseConnectionService dbService, CacheService cacheService) {
        logger.info("👤 UserBusinessService constructor - Top-level business service");
        System.out.println("👤 Creating UserBusinessService (Module B - business logic)");
        
        this.dbService = dbService;
        this.cacheService = cacheService;
        
        System.out.println("✅ UserBusinessService ready to handle business operations");
    }
    
    @Override
    public void createUser(String username) {
        logger.info("Creating user: " + username);
        
        // Use database connection
        if (!dbService.isConnected()) {
            throw new IllegalStateException("Database must be connected!");
        }
        
        System.out.println("📝 Creating user: " + username);
        System.out.println("🔗 Using: " + dbService.getConnectionInfo());
        
        // Cache user info
        cacheService.put("user:" + username, "User data for " + username);
        System.out.println("💾 User cached (enabled: " + cacheService.isEnabled() + ")");
    }
    
    @Override
    public String getUserInfo(String username) {
        // Try cache first
        String cached = cacheService.get("user:" + username);
        if (cached != null) {
            System.out.println("🎯 Cache hit for user: " + username);
            return cached;
        }
        
        // Fallback to database
        System.out.println("🗄️ Cache miss, fetching from database: " + username);
        return "User data for " + username + " (from DB)";
    }
}

// ===== MODULE DEFINITIONS =====

// Module A: Foundation/Infrastructure Module
class InfrastructureModule extends AbstractModule {
    private static final Logger logger = Logger.getLogger(InfrastructureModule.class.getName());
    
    @Override
    protected void configure() {
        logger.info("🏗️ Configuring Infrastructure Module (Module A)");
        System.out.println("\n🏗️ === Configuring Module A (Infrastructure) ===");
        
        // Bind core infrastructure services
        bind(ConfigurationService.class).to(ConfigurationServiceImpl.class);
        bind(DatabaseConnectionService.class).to(DatabaseConnectionServiceImpl.class);
        
        logger.info("Infrastructure bindings configured");
        System.out.println("✅ Module A bindings configured");
    }
    
    // Provide named configurations that Module B might need
    @Provides
    @Named("module.a.ready")
    public Boolean provideModuleAReadyFlag(ConfigurationService configService, DatabaseConnectionService dbService) {
        boolean ready = configService.isInitialized() && dbService.isConnected();
        logger.info("Module A readiness check: " + ready);
        return ready;
    }
}

// Module B: Business Logic Module (depends on Module A)
class BusinessLogicModule extends AbstractModule {
    private static final Logger logger = Logger.getLogger(BusinessLogicModule.class.getName());
    
    @Override
    protected void configure() {
        logger.info("🏢 Configuring Business Logic Module (Module B)");
        System.out.println("\n🏢 === Configuring Module B (Business Logic) ===");
        
        // These bindings depend on Module A services being available
        bind(CacheService.class).to(CacheServiceImpl.class);
        bind(UserBusinessService.class).to(UserBusinessServiceImpl.class);
        
        logger.info("Business logic bindings configured");
        System.out.println("✅ Module B bindings configured");
    }
    
    // Verify Module A is ready before allowing Module B services
    @Provides
    @Named("module.b.validation")
    public String provideModuleBValidation(@Named("module.a.ready") Boolean moduleAReady) {
        if (!moduleAReady) {
            throw new IllegalStateException("Module A must be fully initialized before Module B!");
        }
        logger.info("Module B validation passed - Module A is ready");
        return "Module B validated against Module A";
    }
}

// ===== MAIN APPLICATION =====
public class GuiceModuleDependenciesDemo {
    private static final Logger logger = Logger.getLogger(GuiceModuleDependenciesDemo.class.getName());
    
    public static void main(String[] args) {
        configureLogging();
        
        System.out.println("=== Guice Module Dependencies Demo ===");
        System.out.println("Demonstrating Module A -> Module B dependency chain\n");
        
        try {
            // CORRECT ORDER: Module A (Infrastructure) before Module B (Business Logic)
            demonstrateCorrectModuleOrder();
            
            System.out.println("\n" + "=".repeat(50));
            
            // Show what happens with wrong order (commented out to avoid crash)
            // demonstrateIncorrectModuleOrder();
            
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void demonstrateCorrectModuleOrder() {
        System.out.println("🔄 Creating injector with CORRECT module order...");
        System.out.println("   (Infrastructure Module A → Business Logic Module B)");
        
        // Create injector with proper module order - A before B
        Injector injector = Guice.createInjector(
            new InfrastructureModule(),    // Module A - must come first
            new BusinessLogicModule()      // Module B - depends on A
        );
        
        System.out.println("\n✅ Injector created successfully with proper dependencies!");
        
        // Verify the dependency chain works
        System.out.println("\n🧪 Testing the dependency chain...");
        
        // Get the top-level business service (this will trigger the entire chain)
        System.out.println("\n📦 Requesting UserBusinessService...");
        UserBusinessService userService = injector.getInstance(UserBusinessService.class);
        
        // Verify Module B validation
        String validation = injector.getInstance(Key.get(String.class, Names.named("module.b.validation")));
        System.out.println("🔍 Module B validation result: " + validation);
        
        // Use the business service
        System.out.println("\n🚀 Using business services...");
        userService.createUser("alice");
        userService.createUser("bob");
        
        System.out.println("\n🔍 Retrieving user info...");
        System.out.println("👤 Alice: " + userService.getUserInfo("alice"));
        System.out.println("👤 Bob: " + userService.getUserInfo("bob"));
        System.out.println("👤 Charlie (not cached): " + userService.getUserInfo("charlie"));
        
        System.out.println("\n✅ All operations completed successfully!");
    }
    
    // Uncomment this method to see what happens with wrong module order
    /*
    private static void demonstrateIncorrectModuleOrder() {
        System.out.println("🔄 Creating injector with INCORRECT module order...");
        System.out.println("   (Business Logic Module B → Infrastructure Module A) - This should fail!");
        
        try {
            // This would fail because BusinessLogicModule tries to use services
            // from InfrastructureModule before they're properly configured
            Injector injector = Guice.createInjector(
                new BusinessLogicModule(),     // Module B - tries to use A's services
                new InfrastructureModule()     // Module A - comes too late
            );
        } catch (Exception e) {
            System.out.println("❌ Failed as expected: " + e.getMessage());
        }
    }
    */
    
    private static void configureLogging() {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.INFO);
        
        // Configure Guice loggers
        Logger.getLogger("com.google.inject").setLevel(Level.INFO);
        
        // Clear existing handlers
        for (var handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }
        
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        consoleHandler.setFormatter(new SimpleFormatter() {
            @Override
            public String format(java.util.logging.LogRecord record) {
                if (record.getMessage().contains("constructor") || 
                    record.getMessage().contains("Configuring") ||
                    record.getMessage().contains("Module")) {
                    return String.format("[LOG] %s%n", record.getMessage());
                }
                return "";
            }
        });
        
        rootLogger.addHandler(consoleHandler);
    }
}
