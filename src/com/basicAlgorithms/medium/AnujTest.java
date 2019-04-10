package com.basicAlgorithms.medium;
import java.util.*;

public class AnujTest {
  public static int solution(int[] ranks) {
    if (ranks == null || ranks.length <= 1) {
      return 0;
    }

    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    // creating a map<rank, freq of rank>
    for (int i = 0; i<ranks.length; i++) {
      if (map.containsKey(ranks[i])) {
        map.put(ranks[i], map.get(ranks[i]) + 1);
      }
      else {
        map.put(ranks[i], 1);
      }
    }

    // iterate over hashmap and check if x+1 exists
    Iterator it = map.entrySet().iterator();

    int countSoldiers = 0;
    while (it.hasNext()) {
      Map.Entry<Integer, Integer> entry = (Map.Entry)it.next();
      // check if key+1 exists in the map
      int rank = entry.getKey();
      if (map.containsKey(rank + 1)) {
        // then get the count of rank soldiers
        countSoldiers += entry.getValue();
      }
    }

    return countSoldiers;
  }

  public static void main (String args[]) {
    int ranks[] = {3, 4, 3, 0, 2, 2, 3, 0, 0, 10, 10, 11};
    System.out.println(solution(ranks));
  }
}
