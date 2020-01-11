package com.imomei;

import com.imomei.string.IMoMei_String;

import java.util.*;

public class Test {
    private String a;
    private String b;

    public static void main(String[] args) {
        Map map = new HashMap<String, String>();
        map.put("1", "11");
        map.put("2", "22");
        map.put("3", "33");
        Collection values = map.values();
        Iterator iterator = values.iterator();
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> iterator1 = arrayList.iterator();

        if (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


}
