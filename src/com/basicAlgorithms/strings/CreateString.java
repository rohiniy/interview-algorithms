package com.basicAlgorithms.strings;
import java.util.*;

public class CreateString {
  public static String productName(List<String> names) {
    // Write your code here
    if (names == null || names.size() == 0) {
      return "";
    }
    String str = "";
    int [] charFreq = new int[26];
    for (int i= 0 ;i < names.get(0).length(); i++) {
      Arrays.fill(charFreq, 0);
      boolean isPresentCharWith0 = false;
      for (String s: names) {
        char c = s.charAt(i);
        charFreq[c - 'a']++;
      }
      int j = 25;
      for (j= 25;j > 0; j--) {
        if (charFreq[j] == 0) {
          char resultC = (char)(j + 'a');
          str = str.concat("" + resultC);
          isPresentCharWith0 = true;
          break;
        }
      }
      if (j == 0 && !isPresentCharWith0) {
        int min = Integer.MAX_VALUE;
        int index = 25;
        // then get the least count alphabet
        for (j= 25;j > 0; j--) {
          // get the max number index
          if (min > charFreq[j]) {
            min = charFreq[j];
            index = j;
          }
        }
        // now we have the min frequency alphabet so take that
        char resultC = (char)(index + 'a');
        str = str.concat("" + resultC);
      }
    }
    return str;
  }

  public static void main(String args[]) {
    List<String> list = new ArrayList<>();
//    list.add("abcde");
//    list.add("bcdef");
//    list.add("cdefg");
//    list.add("defgh");
//    list.add("efghi");
//    list.add("fghij");

    for (int i= 0; i< 26; i++) {
      list.add("" + (char)(i+'a'));
    }
    list.add("z");

    System.out.println(productName(list));
  }

}
