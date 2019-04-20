package net.codetojoy.service;

import java.util.*;

import net.codetojoy.cache.*;

public class ItemService {
    UserEditCache userEditCache = new UserEditCache();

    public void edit(long itemId, String userId) {
        System.out.println("TRACER edit item: " + itemId + " user: " + userId);
        Set<UserEditKey> others = userEditCache.findOtherEditors(itemId, userId);

        for (UserEditKey otherEditor : others) {
            System.out.println("WARNING! also being edited by: " + otherEditor.getUserId());
        }
        userEditCache.setAsEditor(itemId, userId);
    }

    public void finalize(long itemId, String userId) {
        System.out.println("TRACER finalize item: " + itemId + " user: " + userId);

        for (UserEditKey key : userEditCache.getKeys()) {
            if (key.isMatch(itemId)) {
                userEditCache.remove(key);
            }
        }
    }

    public void list() {
        System.out.println("keys: [");

        for (UserEditKey key : userEditCache.getKeys()) {
            System.out.println("key: " + key);
        }

        System.out.println("]");

        userEditCache.logStats();
    }

    public void view(long itemId, String userId) {
        System.out.println("TRACER view item: " + itemId + " user: " + userId);
    }
}
