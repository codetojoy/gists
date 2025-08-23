package net.codetojoy.sandbox;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import java.util.logging.Logger;

import static net.codetojoy.sandbox.util.MyLog.buildLog;

// Infrastructure Module
public class InfrastructureModule extends AbstractModule {
    private static final Logger logger = Logger.getLogger(InfrastructureModule.class.getName());
    
    @Override
    protected void configure() {
        bind(DBService.class).to(DBServiceImpl.class);

        requestStaticInjection(DBServiceImpl.class);
        
        logger.info(buildLog("InfrastructureModule: init OK"));
    }
    
    // Provide named configurations that BusinessLogic Module might need
    @Provides
    @Named("module.infra.ready")
    public Boolean provideModuleAReadyFlag(DBService dbService) {
        logger.info(buildLog("InfrastructureModule: checking for ready"));
        return dbService.isInitialized();
    }
}
