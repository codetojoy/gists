package net.codetojoy.cache;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.*;

import net.codetojoy.pojo.*;

public class SimpleItemCache implements ItemCache {
    private static final Map<Integer,Item> cache = new ConcurrentHashMap<>();
    private final ClearCachePredicate clearCachePredicate;
    private static final AtomicInteger fetchCount = new AtomicInteger(0);

    public SimpleItemCache(ClearCachePredicate clearCachePredicate) {
        this.clearCachePredicate = clearCachePredicate;
    }

    @Override
    public Item getItem(int id) {
        Item item = cache.get(id);

        int thisFetchCount = fetchCount.getAndIncrement();
        boolean doCheck = (thisFetchCount >= clearCachePredicate.getPredicateCheckFrequency());

        if (doCheck && clearCachePredicate.doClearCache()) {
            System.out.println("TRACER clearing cache");
            clear();
            fetchCount.getAndSet(0);
        }

        return item;
    }

    @Override
    public void putItem(int id, Item item) {
       cache.putIfAbsent(id, item);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public Set<Integer> getIds() {
        return cache.keySet();
    }
}
