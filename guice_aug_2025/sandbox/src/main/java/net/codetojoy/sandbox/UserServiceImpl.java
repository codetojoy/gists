
package net.codetojoy.sandbox;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.logging.Logger;

@Singleton
class UserServiceImpl implements UserService {
    private boolean initialized = false;
    private final DBService dbService;

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    
    @Inject
    public UserServiceImpl(DBService dbService) {
        logger.info("TRACER ctor");
        this.dbService = dbService;
        initialized = true;
    }

    @Override
    public String getUser(String id) {
        return "user OK [ " + dbService.query(id) + " ]";
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }
}

