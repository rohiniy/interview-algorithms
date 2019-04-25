package com.datastructure.trie;

public class TrieNode {
  TrieNode[] children = new TrieNode[Trie.ALPHABET_SIZE];

  // isEndOfWord is true if the node represents
  // end of a word
  boolean isEndOfWord;

  TrieNode() {
    isEndOfWord = false;
    for (int i = 0; i < Trie.ALPHABET_SIZE; i++) {
      children[i] = null;
    }
  }
}

