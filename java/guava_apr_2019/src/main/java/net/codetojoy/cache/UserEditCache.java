package net.codetojoy.cache;

import java.util.concurrent.TimeUnit;
import java.util.*;

import com.google.common.cache.*;

class UserEditKeyRemovalListener implements RemovalListener<UserEditKey,UserEditKey> {
    @Override
    public void onRemoval(RemovalNotification<UserEditKey,UserEditKey> notification) {
        System.out.println("TRACER removing: " + notification.getKey().toString());
    }
}

public class UserEditCache {
    private Cache<UserEditKey, UserEditKey> cache;
    private CacheStatsLogger cacheStatsLogger = new CacheStatsLogger();

    public UserEditCache() {
        cache = CacheBuilder.newBuilder()
                            .maximumSize(20)
                            .expireAfterAccess(30, TimeUnit.SECONDS)
                            .recordStats()
                            .removalListener(new UserEditKeyRemovalListener())
                            .build();
    }

    public Set<UserEditKey> findOtherEditors(long itemId, String userId) {
        Set<UserEditKey> others = new HashSet<UserEditKey>();

        for (UserEditKey key : getKeys()) {
            if (key.isMatch(itemId)) {
                others.add(key);
            }
        }

        return others;
    }

    public void setAsEditor(long itemId, String userId) {
        UserEditKey key = new UserEditKey(itemId, userId);
        cache.put(key, key);
    }

    public Set<UserEditKey> getKeys() {
        return cache.asMap().keySet();
    }

    public void remove(UserEditKey userEditKey) {
        cache.invalidate(userEditKey);
    }

    public void logStats() {
        cacheStatsLogger.logStats(cache.stats());
    }
}
