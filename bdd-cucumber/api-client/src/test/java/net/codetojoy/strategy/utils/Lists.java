package net.codetojoy.strategy.utils;

import java.util.*;

public class Lists {
    public List<Integer> parseList(String s) {
        var result = new ArrayList<Integer>();
        String[] tokens = s.split(",");
        for (var token : tokens) {
            String intStr = token.trim();
            int value = Integer.parseInt(intStr);
            result.add(value);
        } 
        return result;
    }
}
