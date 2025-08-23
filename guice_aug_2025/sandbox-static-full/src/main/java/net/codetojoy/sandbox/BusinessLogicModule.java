package net.codetojoy.sandbox;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import java.util.logging.Logger;

import static net.codetojoy.sandbox.util.MyLog.buildLog;

// Module B: Business Logic Module (depends on Module A)
public class BusinessLogicModule extends AbstractModule {
    private static final Logger logger = Logger.getLogger(BusinessLogicModule.class.getName());
    
    @Override
    protected void configure() {
        bind(UserService.class).to(UserServiceImpl.class);
        
        logger.info(buildLog("BusinessLogicModule: init OK"));
    }
    
    // Verify Module A is ready before allowing Module B services
    @Provides
    @Named("module.b.validation")
    public String provideModuleBValidation(@Named("module.a.ready") Boolean moduleAReady) {
        if (!moduleAReady) {
            throw new IllegalStateException("Module A must be fully initialized before Module B!");
        }
        logger.info(buildLog("BusiessLogicModule validation passed - DatabaseModule is ready"));
        return "Module B validated against Module A";
    }
}
