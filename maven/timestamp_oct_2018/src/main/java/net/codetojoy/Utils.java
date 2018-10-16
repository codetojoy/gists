
package net.codetojoy;

import org.apache.commons.lang3.StringUtils;

public class Utils {
    public boolean isNumeric(String s) {
        boolean result = false;

        if (s != null) {
            result = StringUtils.isNumeric(s);
        }

        return result;
    }
}
