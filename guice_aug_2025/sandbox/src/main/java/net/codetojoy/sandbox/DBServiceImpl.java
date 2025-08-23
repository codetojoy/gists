
package net.codetojoy.sandbox;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.logging.Logger;

@Singleton
class DBServiceImpl implements DBService {
    private boolean initialized = false;

    private static final Logger logger = Logger.getLogger(DBServiceImpl.class.getName());
    
    @Inject
    public DBServiceImpl() {
        logger.info("TRACER ctor");
        initialized = true;
    }

    @Override
    public String query(String id) {
        return "mock DB query OK for " + id;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }
}

