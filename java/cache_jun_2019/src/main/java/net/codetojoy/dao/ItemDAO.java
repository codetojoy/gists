package net.codetojoy.service;

import java.util.*;
import java.io.File;

import net.codetojoy.cache.*;
import net.codetojoy.pojo.*;

public class ItemDAO implements ClearCachePredicate {
    private ItemCache itemCache = new SimpleItemCache(this);

    @Override
    public boolean doClearCache() {
        boolean result = new File("/Users/measter/tmp/clear.cache.txt").exists();
        return result;
    }

    @Override
    public int getPredicateCheckFrequency() {
        return 0;
    }

    protected String mockFetch(int id) {
        String result = new Date().toString();

        try {
            System.out.println("fetching item id: " + id);
            Thread.sleep(2*1000);
        } catch(Exception ex) {
        }

        return result;
    }

    public Item getItem(int id) {
        Item item = itemCache.getItem(id);

        if (item == null) {
           String payload = mockFetch(id);
           item = new Item(id, payload);
           itemCache.putItem(id, item);
        }

        return item;
    }

    public List<Item> getItems() {
        List<Item> result = new ArrayList<>();

        for (Integer id : itemCache.getIds()) {
            Item item = itemCache.getItem(id);
            result.add(item);
        }

        return result;
    }

    public void clear() {
        itemCache.clear();
    }
}
