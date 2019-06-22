package net.codetojoy.cache;

import java.util.*;

import net.codetojoy.pojo.*;

public interface ItemCache {
    Item getItem(int id);
    void putItem(int id, Item item);
    void clear();
    Set<Integer> getIds();
}
