
import java.util.Map;

public class ExampleNoRegex {
    private static final char ASTERISK = '*';

    String replace(int start, int end, String input) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (i >= start && i <= end) {
                c = ASTERISK;
            }
    
            builder.append(c);
        }

        return builder.toString();
    }

    String maskEmailId(String input) {
        int numChars = input.length();
        int start = 0;
        int end = 0;

        if (numChars == 2) {
            start = 1;
            end = 1;
        } else if (numChars == 3) {
            start = 1;
            end = 1;
        } else if (numChars == 4) {
            start = 1;
            end = 2;
        } else {
            start = 2;
            int lastIndex = numChars - 1;
            end = lastIndex - 2;
        }

        String result = replace(start, end, input);

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
                                            "' expected: '" + expected + 
                                            "' actual: '" + result + "'");
        }
        System.out.println("TRACER result: " + result);
    }

    public static void main(String... args) {
        ExampleNoRegex example = new ExampleNoRegex();

        Map<String,String> inputMap = Map.of(
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

