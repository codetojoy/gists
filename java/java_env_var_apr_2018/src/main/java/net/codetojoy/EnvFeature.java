
package net.codetojoy;

public class EnvFeature {
    public static final String ACME_CLIENT_ABC = "ACME_CLIENT_ABC";
    public static final String ACME_SERVER_DEF = "ACME_SERVER_DEF";

    protected static final String ON_VALUE = "TRUE";

    private EnvSource envSource = new EnvSource();

    public boolean isOn(String name) {
        boolean result = false;

        if (name != null) {
            String value = envSource.getenv(name);

            result = ON_VALUE.equalsIgnoreCase(value);
        }

        return result;
    }

    // testing only 
    protected void setEnvSource(EnvSource envSource) {
        this.envSource = envSource;
    }
}
