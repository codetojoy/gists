
package net.codetojoy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilsTestCase {

    @ParameterizedTest(name = "{index} => s={0}, expected={1}, note={2}")
    @MethodSource("paramProvider")
    void testIsUUID(String s, boolean expected, String note) {
        // test
        boolean result = StringUtils.isUUID(s);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> paramProvider() {
        return Stream.of(
Arguments.of("d8bebfbc-aea2-4c13-9122-264217042fa3", true, "basic"),
Arguments.of("d8bebfbc-aea2-4c13-9122-264217042fa3", true, "group3[0] == 4"),
Arguments.of("d8bebfbc-aea2-6c13-9122-264217042fa3", false, "group3[0] != 4"),
Arguments.of("d8bebfbc-aea2-4c13-8122-264217042fa3", true, "group4[0] in {8,9,A,B}"),
Arguments.of("d8bebfbc-aea2-4c13-2122-264217042fa3", false, "group4[0] NOT in {8,9,A,B}")
        );
    }
}
