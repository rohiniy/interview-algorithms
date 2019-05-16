package com.datastructure.trie;

import java.util.ArrayList;
import java.util.List;

public class Trie {
  static final int ALPHABET_SIZE = 26;
  private TrieNode root;

  // If not present, inserts key into trie 
  // If the key is prefix of trie node,  
  // just marks leaf node 
  public void insert(String key) {
    int i;
    int length = key.length();
    int index;

    TrieNode current = root;

    for (i = 0; i < length; i++) {
      index = key.charAt(i) - 'a';
      if (current.children[index] == null)
        current.children[index] = new TrieNode();

      current = current.children[index];
    }

    // mark last node as leaf 
    current.isEndOfWord = true;

  }

  public boolean search(String key) {
    TrieNode current = root;
    int index;

    for (int i=0; i< key.length(); i++) {
      index = key.charAt(i) - 'a';
      if (current.children[index] == null) {
        return false;
      }
      current = current.children[index];
    }

    // check if is end of word
    return current != null && current.isEndOfWord;
  }

  public List<String> autoComplete(String str) {
    TrieNode current = root;
    List<String> result = new ArrayList<>();
    StringBuffer word = new StringBuffer();

    for (int i= 0; i< str.length(); i++) {
      int index = str.charAt(i) - 'a';
      word.append(str.charAt(i));
      if (current.children[index] == null) {
        return result;
      }
      current = current.children[index];
    }

    traverseRec(word.toString(), current, result);
    return result;
  }

  public List<String> traverseRec(String s, TrieNode current, List<String> result) {
    if (current.isEndOfWord) {
      result.add(s);
    }
    for (char i = 'a'; i< 'z'; i++) {
      if (current.children[i-'a'] != null) {
        traverseRec(s+i, current.children[i-'a'], result);
      }
    }

    return result;
  }
  public static void main(String args[]) {
    String keys[] = {"the", "a", "there", "answer", "any",
        "by", "bye", "their"};

    Trie trie = new Trie();
    trie.root = new TrieNode();

    // Construct trie
    int i;
    for (i = 0; i < keys.length ; i++) {
      trie.insert(keys[i]);
    }

    boolean isPresent = trie.search("ther");
    System.out.println(isPresent);


    System.out.println("Autocomplete::");
    System.out.println(trie.autoComplete("the"));

  }
}
