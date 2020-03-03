
package net.codetojoy.util;

public class KeyManager {
    private static KeyManagerApi keyManagerApi = new DatabaseKeyManagerImpl();

    public static void setKeyManagerApi(KeyManagerApi keyManagerApi) {
        KeyManager.keyManagerApi = keyManagerApi;
    }

    public static String getValue(String key) {
        return keyManagerApi.getValue(key);
    }
}
