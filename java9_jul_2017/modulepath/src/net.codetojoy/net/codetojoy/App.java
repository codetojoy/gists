
package net.codetojoy;

import com.fasterxml.jackson.databind.*;

class Composer {
    public String name;
    public String era;

    public String toString() {
        return "composer " + name + " lived in " + era + " era";
    }
}

public class App {
    @SuppressWarnings("unchecked")
    public static Object getClass(String className) {
        Object result = null;

        try {
            Class c = Class.forName(className);
            result = c.getConstructor().newInstance(); 
        } catch (Exception ex) {
            throw new IllegalStateException("could not find: " + className);
        }

        return result;
    }

    public static void main(String... args) throws Exception {
        Object obj = getClass("com.fasterxml.jackson.databind.ObjectMapper");
        ObjectMapper mapper = (ObjectMapper) obj;
        String json = "{\"name\" : \"Mozart\", \"era\": \"Classical\"}";

        Composer composer = mapper.readValue(json, Composer.class);
        System.out.println(composer.toString());
    }
}

