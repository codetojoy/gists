
package net.codetojoy.sandbox;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.logging.Logger;

import static net.codetojoy.sandbox.util.Constants.LOG_FORMAT;

@Singleton
public class UserServiceImpl implements UserService {
    private boolean initialized = false;
    private final DBService dbService;

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    
    @Inject
    public UserServiceImpl(DBService dbService) {
        this.dbService = dbService;

        if (!dbService.isInitialized()) {
            throw new IllegalStateException("dbService not initialized !?");
        }

        initialized = true;
    }

    @Override
    public String getUser(String id) {
        return String.format(LOG_FORMAT, "UserServiceImpl", dbService.query(id));
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }
}

