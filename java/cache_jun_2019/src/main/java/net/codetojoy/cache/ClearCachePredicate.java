package net.codetojoy.cache;

public interface ClearCachePredicate {
    boolean doClearCache();

    int getPredicateCheckFrequency();
}
