
package net.codetojoy;

public class StringUtils {
    // match a string that
    // comprises hexidecimal characters broken into 5 groups of 8, 4, 4, 4, & 12 chars separated by dashes
    // the first character of the third group is a 4 (because v4 uuid)
    // the first char of the fourth group is one of 8, 9, A or B (because uuid)
    // the alpha characters are case insensitive

    protected static final String UUID_REGEX = "(?i)^[0-9A-F]{8}-[0-9A-F]{4}-[4][0-9A-F]{3}-[89AB][0-9A-F]{3}-[0-9A-F]{12}$";

    // protected final static Pattern regex = Pattern.compile(regex);
 
    public static boolean isUUID(String maybeUuid) {
        return maybeUuid.matches(UUID_REGEX);
    }
}
