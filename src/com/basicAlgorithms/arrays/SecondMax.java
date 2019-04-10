package com.basicAlgorithms.arrays;
import java.util.*;

public class SecondMax {
  public static int alphaBeta(List<Integer> pile) {

    if (pile == null || pile.size() == 0) {
      return 0;
    }
    // Write your code here
    // finding the 2nd max of the array
    int max = Integer.MIN_VALUE;
    int max2 =  Integer.MIN_VALUE;
    for (int i = 0; i< pile.size(); i++) {
      if (max < pile.get(i)) {
        max2 = max;
        max = pile.get(i);
      }
      else if (max == pile.get(i)) {
        //if same as max
        continue;
      }
      else if (max2 < pile.get(i)) {
        // if max is greater and max2 is less
        max2 = pile.get(i);
      }
    }

    return max2 == Integer.MIN_VALUE ? 0 : max2;
  }


  public static void main(String args[]) {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(1);
    list.add(1);
    list.add(1);
    list.add(1);
    list.add(1);


//    list.add(1);
//    list.add(2);
//    list.add(4);
//    list.add(3);
//    list.add(3);
//    list.add(4);

    int result = alphaBeta(list);
    System.out.println(result);

  }

}
