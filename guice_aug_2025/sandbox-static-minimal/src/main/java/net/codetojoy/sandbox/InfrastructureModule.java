package net.codetojoy.sandbox;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import java.util.logging.Logger;

// Module A: Foundation/Infrastructure Module
class InfrastructureModule extends AbstractModule {
    private static final Logger logger = Logger.getLogger(InfrastructureModule.class.getName());
    
    @Override
    protected void configure() {
        logger.info("TRACER Module A (InfrastructureModule): init begin");
        
        bind(DBService.class).to(DBServiceImpl.class);

        requestStaticInjection(DBServiceImpl.class);
        
        logger.info("TRACER Module A (InfrastructureModule): init OK");
    }
    
    // Provide named configurations that Module B might need
    @Provides
    @Named("module.a.ready")
    public Boolean provideModuleAReadyFlag(DBService dbService) {
        return dbService.isInitialized();
    }
}
