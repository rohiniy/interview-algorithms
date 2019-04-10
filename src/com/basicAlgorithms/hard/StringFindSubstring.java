/**
 * You are given a string, s, and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words
 * exactly once and without any intervening characters.
 *
 * Example 1:
 *
 * Input:
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 *
 * Example 2:
 *
 * Input:
 *   s = "wordgoodgoodgoodbestword",
 *   words = ["word","good","best","word"]
 * Output: []
 */
package com.basicAlgorithms.hard;

import java.util.*;

public class StringFindSubstring {
  /**
   * 108 ms (56% better)
   *
   * @param s
   * @param words
   * @return
   */
  public static List<Integer> findSubstringCorrectEfficient(String s, String[] words) {
    List<Integer> result = new ArrayList<>();
    if (words == null || words.length == 0 || s.length() == 0) return result;
    int length = words[0].length();
    if (s.length() < words.length * length) {
      return result;
    }

    HashMap<String, Integer> remainMap = new HashMap<>(words.length); // contains the remaining words
    HashMap<String, Integer> copyMap = new HashMap<>(words.length);
    HashSet<Character> setOfStartingLetter = new HashSet<>(words.length);

    for (int i = 0; i < words.length; i++) {
      setOfStartingLetter.add(words[i].charAt(0));
      if (remainMap.containsKey(words[i])) {
        // add count
        remainMap.put(words[i], remainMap.get(words[i]) + 1);
      } else {
        remainMap.put(words[i], 1);
      }
    }

    copyMap.putAll(remainMap);


    for (int i = 0; i <= s.length() - length; i++) {
      if (!setOfStartingLetter.contains(s.charAt(i))) {
        continue;
      }
      String str = s.substring(i, i + length);

      if (remainMap.containsKey(str)) {
        int freq = remainMap.get(str);
        if (remainMap.get(str) == 1) {
          remainMap.remove(str);
        } else {
          remainMap.put(str, freq - 1);
        }

        int p = i + length;
        while (remainMap.size() > 0 && p <= s.length() - length) {
          String substring = s.substring(p, p + length);

          if (remainMap.containsKey(substring)) {
            int count = remainMap.get(substring);
            if (count == 1) {
              remainMap.remove(substring);
            } else {
              // decrement count
              remainMap.put(substring, count - 1);
            }
            p = p + length;
          } else {
            break;
          }
        }
        if (remainMap.size() == 0) {
          result.add(i);
          i = i + length - 1;
        }
        remainMap.putAll(copyMap);
      }
    }
    return result;
  }
  public static List<Integer> findSubstring(String s, String[] words) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    if(s==null||s.length()==0||words==null||words.length==0){
      return result;
    }

    //frequency of words
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    for(String w: words){
      if(map.containsKey(w)){
        map.put(w, map.get(w)+1);
      }else{
        map.put(w, 1);
      }
    }

    int len = words[0].length();

    for(int j=0; j<len; j++){
      HashMap<String, Integer> currentMap = new HashMap<String, Integer>();
      int start = j;//start index of start
      int count = 0;//count total qualified words so far

      for(int i=j; i<=s.length()-len; i=i+len){
        String sub = s.substring(i, i+len);
        if(map.containsKey(sub)){
          //set frequency in current map
          if(currentMap.containsKey(sub)){
            currentMap.put(sub, currentMap.get(sub)+1);
          }else{
            currentMap.put(sub, 1);
          }
          count++;

          while(currentMap.get(sub)>map.get(sub)){
            String left = s.substring(start, start+len);
            currentMap.put(left, currentMap.get(left)-1);

            count--;
            start = start + len;
          }
          if(count==words.length){
            result.add(start); //add to result

            //shift right and reset currentMap, count & start point
            String left = s.substring(start, start+len);
            currentMap.put(left, currentMap.get(left)-1);
            count--;
            start = start + len;
          }
        }else{
          currentMap.clear();
          start = i+len;
          count = 0;
        }
      }
    }

    return result;
  }


  public static void main(String args[]) {
    String words[] = {"word", "good", "best", "good"};
    //"barfoofoobarthefoobarman"
    //["bar","foo","the"]
    //"wordgoodgoodgoodbestword"
    //["word","good","best","word"]
    System.out.println(findSubstring("wordgoodgoodgoodbestword", words));
    //System.out.println(findSubstring("barfoothefoobarman", words));
  }
}
