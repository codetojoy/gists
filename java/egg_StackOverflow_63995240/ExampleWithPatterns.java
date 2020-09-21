
import java.util.*;
import java.util.regex.*;

// requires JDK 11+

public class ExampleWithPatterns {
    private static final String ASTERISK = "*";

    private static final Pattern PATTERN_2 = Pattern.compile(".(.)");
    private static final Pattern PATTERN_3 = Pattern.compile(".(.).");
    private static final Pattern PATTERN_4 = Pattern.compile(".(..).");
    private static final Pattern PATTERN_N = Pattern.compile("..(.*)..");
    
    String replace(Pattern regex, String replacement, String input) {
        String result = "N/A";

        Matcher m = regex.matcher(input);
        if (m.find()) {
            int groupToReplace = 1;
            result = new StringBuilder(input).replace(m.start(groupToReplace), m.end(groupToReplace), replacement).toString();
        } else {
            throw new IllegalStateException("internal error");
        }

        return result;
    }

    String maskEmailId(String input) {
        int numChars = input.length();
        Pattern regex = null;
        String replacement = "";

        if (numChars == 2) {
            regex = PATTERN_2;
            replacement = ASTERISK;
        } else if (numChars == 3) {
            regex = PATTERN_3;
            replacement = ASTERISK;
        } else if (numChars == 4) {
            regex = PATTERN_4;
            replacement = ASTERISK + ASTERISK;
        } else {
            regex = PATTERN_N;
            int numAsterisks = numChars - 4;

            // requires JDK 11+
            replacement = ASTERISK.repeat(numAsterisks);
        }

        String result = replace(regex, replacement, input);

        return result;
    }

    String maskEmailAddress(String input) {
        String[] tokens = input.split("@");
        String frontToken = tokens[0];
        String backToken = tokens[1];
        return maskEmailId(frontToken) + "@" + backToken;
    }

    void runExample(String input, String expected) {
        String result = maskEmailAddress(input);

        if (! result.equals(expected)) {
            throw new IllegalStateException("for input: '" + input + 
                                            "' expected: '" + expected + "'");
        }
        System.out.println("TRACER result: " + result);
    }

    public static void main(String... args) {
        var example = new ExampleWithPatterns();

        var inputMap = Map.of(
                        "qwerty@gmail.com","qw**ty@gmail.com",
                        "helloworld@gmail.com","he******ld@gmail.com",
                        "stackoverflow@gmail.com","st*********ow@gmail.com",
                        "abcde@gmail.com","ab*de@gmail.com",
                        "abcd@gmail.com","a**d@gmail.com",
                        "abc@gmail.com","a*c@gmail.com",
                        "ab@gmail.com","a*@gmail.com"
                        );

        for (String input : inputMap.keySet()) {
            String expected = inputMap.get(input);
            example.runExample(input, expected); 
        }
    }
}

