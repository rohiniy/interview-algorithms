/**
 * Given an array of strings, group anagrams together.
 *
 * Example:
 *
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * Note:
 *
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 *
 *
 * Solution:
 * 1. Using hashcode. Represent each string in the array as char[26] as in lowercase then
 * take Arrays.hashcode(char array). Put this hashcode in the hashmap<hashcode, ArrayList<strings>>
 * Now just print the array list as they will be grouped as per same hashcode i.e. same letters.
 * - Runtime = 14 ms
 * 2.
 */
package com.basicAlgorithms.medium;

import java.util.*;

public class StringGroupAnagrams {
  /**
   * Use hashcode : Arrays.hashcode(charArray[26 with count set for each letter])
   * This will not preserve the order
   * @param strs
   * @return
   */
  public static List<List<String>> groupAnagramsUsingHashCode(String[] strs) {

    if (strs == null || strs.length == 0) {
      return null;
    }
    HashMap<Integer, ArrayList<String>> map = new HashMap<>();

    for (String str: strs) {
      int charArr[] = new int[26];
      // take each string's char array representation in char[26] and then take hashcode
      for (char c: str.toCharArray()) {
        charArr[c-'a']++;
      }

      // take hashcode
      int hashcode = Arrays.hashCode(charArr);
      if (!map.containsKey(hashcode)) {
        map.put(hashcode, new ArrayList<String>());
      }

      map.get(hashcode).add(str);
    }

    return new ArrayList<>(map.values());
  }

  public static void main(String args[]) {
    String [] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
    List<List<String>> list = groupAnagramsUsingHashCode(strs);

    System.out.println(list);
  }
}
