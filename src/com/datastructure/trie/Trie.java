package com.datastructure.trie;

public class Trie {
  static final int ALPHABET_SIZE = 26;
  private TrieNode root;

  // If not present, inserts key into trie 
  // If the key is prefix of trie node,  
  // just marks leaf node 
  public void insert(String key) {
    int i = 0;
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
    int index = 0;

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

  }
}
