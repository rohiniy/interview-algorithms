package com.basicAlgorithms.medium;
import java.util.*;


public class SearchStringSubstringInSet {

    public static void main(String args[] ) throws Exception {
      /* Enter your code here. Read input from STDIN. Print output to STDOUT */

      // hotelfate

      String str = "hotelfate";
      Set<String> stringSet = new HashSet<>();
      stringSet.add("hotel");
      stringSet.add("hot");
      stringSet.add("fate");
      stringSet.add("fat");
      isPresent(str, stringSet, 0, str.length());

    }


    public static boolean isPresent(String str, Set<String> set, int mid, int end) {
      //hotelfate
      // f           && f
      // isPresebtHelper(h) + otelfate
      // isPresentHelper(ho) + telfate
      // hot +  isPresent(elfate)
      // hote +
      //
      if (mid > end) {
        return false;
      }

      if (set.contains(str.substring(0, mid+1)) && set.contains(str.substring(mid+1, end))) {
        return true;
      }
      else {
        // hotel + fate
        return isPresent(str, set, mid+1, end);
      }
    }
}
