
package net.codetojoy.sandbox;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.logging.Logger;

@Singleton
class DBServiceImpl implements DBService {
    private static NastyDB nastyDB;

    private static final Logger logger = Logger.getLogger(DBServiceImpl.class.getName());
    
    @Inject 
    public static void setNastyDB(NastyDB nastyDB) {
        DBServiceImpl.nastyDB = nastyDB;
    }

    @Override
    public String query(String id) {
        return "{\"who\":\"DBServiceImpl\", \"status\":" + nastyDB.query(id) + "}";
    }

    @Override
    public boolean isInitialized() {
        return nastyDB.isInitialized();
    }
}

