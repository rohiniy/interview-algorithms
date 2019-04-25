package com.test;

import java.util.*;

/**
 * Class to define the Vowel
 * letter with its count
 */
class VowelsCount {
  int count;
  char vowel;

  public VowelsCount(int count, char vowel) {
    this.count = count;
    this.vowel = vowel;
  }
}

class Solution {

//  public String solution(String S) {
//    if (S.length() == 0) {
//      return "";
//    }
//
//    StringBuilder result = new StringBuilder();
//    // Convert the string in lowercase
//    S = S.toLowerCase().replaceAll(" ", "");
//
//    Map<VowelsCount, Integer> vowelsCountMap = new TreeMap<>(new Comparator<VowelsCount>() {
//      @Override
//      public int compare(VowelsCount o1, VowelsCount o2) {
//        if (o1.equals(o2)) {
//          return 0;
//        }
//        if (o1.count < o2.count) {
//          return 1;
//        }
//        else if (o1.count > o2.count) {
//          return -1;
//        }
//        else {
//          // if they are equal then return the lexicographical order
//          return o1.vowel - o2.vowel;
//        }
//      }
//    });
//
//
//
//    // write your code in Java SE 8
//    for (char c: S.toCharArray()) {
//      //for each character create an object and store in TreeSet<VowelsCount>
//      if (isVowel(c)) {
//        VowelsCount vowelsCountObj = new VowelsCount(1,c);
//
//        if (!vowelsCountMap.containsKey(vowelsCountObj)) {
//          vowelsCountMap.put(vowelsCountObj, 1);
//        }
//        else {
//          // it is a vowel character then add in Map with incremented count
//          int freqCount = vowelsCountMap.get(vowelsCountObj);
//
//          // remove the prev entry
//          vowelsCountMap.remove(vowelsCountObj);
//
//          // increment count
//          vowelsCountObj.count = freqCount + 1;
//
//          // put the object in map for sorting
//          vowelsCountMap.put(vowelsCountObj, freqCount+1);
//        }
//
//      }
//    }
//
//    // check if the 1st entry is highest
//    Integer listCount[] = vowelsCountMap.values().toArray(new Integer[vowelsCountMap.size()]);
//
//
//    if (listCount.length == 1 || listCount.length > 1 && listCount[0] > listCount[1]) {
//      // we have the single common vowel
//      result.append(((TreeMap<VowelsCount, Integer>) vowelsCountMap).firstKey().vowel);
//      result.append(" appears " + listCount[0]);
//      if (listCount[0] > 1) {
//        result.append(" times");
//      }
//      else {
//        result.append(" time");
//      }
//      return result.toString();
//    }
//
//    int countOfCommon = 1;
//    for (int i=1; i< listCount.length; i++) {
//      if (listCount[i] == listCount[0]) {
//        countOfCommon++;
//      }
//    }
//
//    // iterate over TreeMap to get the highest count
//    for (VowelsCount vowelsCount: vowelsCountMap.keySet()) {
//      if (vowelsCount.count == listCount[0]) {
//        result.append(vowelsCount.vowel + " appears " + listCount[0]);
//        if (vowelsCount.count > 1) {
//          result.append(" times");
//        }
//        else {
//          result.append(" time");
//        }
//        countOfCommon--;
//
//        if (countOfCommon == 0) {
//          break;
//        }
//        else {
//          result.append("\n");
//        }
//      }
//    }
//
//    return result.toString();
//  }

  /**
   * function to determine if a given character is vowel
   * @param c
   * @return
   */
  private boolean isVowel(char c) {
    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
      return true;
    }
    return false;
  }

  public String solution (String S) {
    S = S.toLowerCase().replaceAll(" ", "");
    Map<Character, Integer> map = new HashMap<>();

    for (char c: S.toCharArray()) {
      if (isVowel(c)) {
        int count = map.getOrDefault(c, 0);
        map.put(c, count+1);
      }
    }

    // map <a 2, e 3>
    // List <obj>
    List<Map.Entry<Character, Integer>> list = new ArrayList<>();
    for (Map.Entry<Character, Integer> entry: map.entrySet()) {
      list.add(entry);
    }
    Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
      @Override
      public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
        if (o2.getValue() > o1.getValue()) {
          return 1;
        }
        else if (o2.getValue() < o1.getValue()) {
          return -1;
        }
        else {
          // equal
          return o1.getKey().compareTo(o2.getKey());
        }
      }
    });

    int maxCount = 0;
    StringBuilder result = new StringBuilder();
    if (list.size() > 0) {
      Map.Entry<Character, Integer> entry = list.get(0);
      maxCount = entry.getValue();

      if (list.size() > 1 && list.get(1).getValue() != maxCount) {
        // ony 1 max entry
        result.append(entry.getKey() + " appears " + maxCount);
        if (maxCount > 1) {
          result.append(" times");
        }
        else {
          result.append(" time");
        }
        return result.toString();
      }

      int countOfSameFreq = 1;
      for (int i=1; i< list.size(); i++) {
        if (list.get(i).getValue() == maxCount) {
          countOfSameFreq++;
        }
      }

      for (int i=0; i< countOfSameFreq; i++) {
        int count = list.get(i).getValue();
        result.append(list.get(i).getKey() + " appears " + list.get(i).getValue());
        if (count > 1) {
          result.append(" times");
        }
        else {
          result.append(" time");
        }
        if (i != countOfSameFreq-1) {
          result.append("\n");
        }
      }

    }
    return result.toString();
  }

  public static void main(String args[]) {
    Solution obj = new Solution();
    System.out.println(obj.solution("this is a sentence"));
  }

}
