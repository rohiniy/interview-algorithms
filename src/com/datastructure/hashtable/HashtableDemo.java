package com.datastructure.hashtable;

import java.util.*;

class HashtableDemo {
  public static void main(String[] arg)
  {
    // creating a hash table
    Hashtable h = new Hashtable<>();
    Hashtable h1 = new Hashtable();

    h.put("3", "Geeks");
    h.put(2, "forGeeks");
    h.put(1, "isBest");

    // create a clone or shallow copy of hash table h
//    h1 = (Hashtable)h.clone();
//
//    // checking clone h1
//    System.out.println("values in clone: " + h1);
//
//    // clear hash table h
//    h.clear();

    // checking hash table h
//    System.out.println("after clearing: " + h);
    Iterator it = h.keySet().iterator();
    while(it.hasNext()) {
      System.out.println(it.next());
      it.remove();
    }

    int n = (int) Math.pow(2,34);
    //HashMap<Integer, Integer> map = new HashMap((int)Math.pow(2,32), 0.75f);
    //map.put(1, 1);
    System.out.println(Math.pow(2,31));


  }
}