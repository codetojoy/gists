package net.codetojoy.cache;

import java.io.Serializable;

public class UserEditKey implements Serializable {
    private static final String TOKEN = "::";
    private final String key;
    private final long itemId;
    private final String userId;

    public UserEditKey(long itemId, String userId) {
        key = "" + itemId + TOKEN + userId;
        this.itemId = itemId;
        this.userId = userId;
    }

    public boolean isMatch(long itemId) {
        return this.itemId == itemId;
    }

    public String toString() {
        return key;
    }

    public String getKey() {
        return key;
    }

    public long getItemId() {
        return itemId;
    }

    public String getUserId() {
        return userId;
    }
}
