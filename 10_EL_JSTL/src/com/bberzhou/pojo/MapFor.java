package com.bberzhou.pojo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/6/2021
 * Create By Intellij IDEA
 */
public class MapFor {
    public static void main(String[] args) {
        HashMap<String ,String > hashMap = new HashMap<>();
        hashMap.put("key1","value1");
        hashMap.put("key2","value2");
        hashMap.put("key3","value3");
        hashMap.put("key4","value4");
        Set<String> strings = hashMap.keySet();
        for (String string : strings) {
            System.out.println(string);
        }
        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
        // Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        // while (iterator.hasNext()){
        //     System.out.println(iterator.next());
        // }
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey()+" ,"+entry.getValue());
        }
    }
}
