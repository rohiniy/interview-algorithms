package com.basicAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeMap {
  /** Initialize your data structure here. */
  ArrayList<HashMap<HashMap<String, Integer>, String>> timeMapList;

  public TimeMap() {
    timeMapList = new ArrayList<>();
  }

  public void set(String key, String value, int timestamp) {
    HashMap<String, Integer> innerMap = new HashMap<>();
    innerMap.put(key, timestamp);

    HashMap<HashMap<String, Integer>, String> timeMap = new HashMap<>();
    timeMap.put(innerMap, value);

    timeMapList.add(timeMap);
  }

  public String get(String key, int timestamp) {
    HashMap<String, Integer> innerKey = new HashMap<>();
    innerKey.put(key, timestamp);

    for (HashMap timeMap: timeMapList) {
      if (timeMap.containsKey(innerKey)) {
        return timeMap.get(innerKey).toString();
      }
    }

//    int i = timestamp;
//    // if there is no key
//    while(i > 0) {
//      // decrease timestamp and then see if key exists
//      innerKey.put(key, timestamp -i);
//      if (timeMap.containsKey(innerKey)) {
//        return timeMap.get(innerKey).toString();
//      }
//      i--;
//    }
    return "";
  }

  public static void main(String args[]) {

//["TimeMap","set","get","get","set","get","get"]
//[[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
    TimeMap obj= new TimeMap();
    //obj.set("love", "high", 10);
    obj.set("foo","bar",1);
    obj.get("foo", 1);
    obj.get("foo", 3);
    obj.set("foo","bar2",4);
    System.out.println(obj.get("foo", 4));

  }
}
