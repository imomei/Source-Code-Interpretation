package com.imomei;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Map map = new HashMap<String, String>();
        map.put("1", "11");
        map.put("2", "22");
        map.put("3", "33");
        Collection values = map.values();
        Iterator iterator = values.iterator();
        if (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
