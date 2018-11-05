
package net.codetojoy;

import net.codetojoy.candy.*;

import org.apache.commons.lang3.StringUtils;

public class Utils {
    public String getCandyMessage() {
        return new Candy().getMessage();
    }

    public boolean isNumeric(String s) {
        boolean result = false;

        if (s != null) {
            result = StringUtils.isNumeric(s);
        }

        return result;
    }
}
