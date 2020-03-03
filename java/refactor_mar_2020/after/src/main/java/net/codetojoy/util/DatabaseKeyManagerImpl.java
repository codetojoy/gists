
package net.codetojoy.util;

public class DatabaseKeyManagerImpl implements KeyManagerApi {
    public String getValue(String key) {
        String result = LegacyKeyManager.getValue(key);
        return result;
    }
}
