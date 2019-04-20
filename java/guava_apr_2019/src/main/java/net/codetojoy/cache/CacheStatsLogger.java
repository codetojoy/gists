
package net.codetojoy.cache;

import com.google.common.cache.CacheStats;

public class CacheStatsLogger {
    public void logStats(CacheStats cacheStats) {
        String hitRateStr = String.format("%.2f", cacheStats.hitRate());
        StringBuilder buffer = new StringBuilder();
        buffer.append(" hits: " + cacheStats.hitCount());
        buffer.append(" misses: " + cacheStats.missCount());
        buffer.append(" hitRate: " + hitRateStr);
        buffer.append(" requestCount: " + cacheStats.requestCount());
        System.out.println("TRACER STATS " + buffer.toString());
    }
}
