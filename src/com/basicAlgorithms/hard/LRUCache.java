/**
 *
 */
package com.basicAlgorithms.hard;

import java.util.LinkedHashMap;

public class LRUCache extends LinkedHashMap<Integer, Integer> {



   public static void main(String args[]) {
     LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(5, 0.75F, true);
     map.put(1,10);
     map.put(2,5);
     map.put(3,25);

     map.get(2);
     map.get(1);


   }
}
