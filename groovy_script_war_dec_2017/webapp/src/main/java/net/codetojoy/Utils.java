
package net.codetojoy;

import org.apache.commons.lang3.StringUtils;

public class Utils {
    // just a silly example 
    public int last4Digits(String s) {
        int result = 0;

        if (s != null) {
            if (StringUtils.isNumeric(s)) {
                int tmp = Integer.parseInt(s);
                result = tmp % 10000;
            }
        }

        return result;
    }
}
