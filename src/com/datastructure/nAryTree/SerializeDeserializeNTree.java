/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * For example, you may serialize the following 3-ary tree
 *
 *                               1
 *                       3         2        4
 *                 5       6
 *
 *
 * as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 *
 *
 *
 * Note:
 *
 *     N is in the range of [1, 1000]
 *     Do not use class member/global/static variables to store states.
 *     Your serialize and deserialize algorithms should be stateless.
 *
 *
 * SOLUTION:
 * Root node val, children size, children node, children node's children size
 */
package com.datastructure.nAryTree;

import java.util.*;

public class SerializeDeserializeNTree {
  // Encodes a tree to a single string.
  public String serialize(NAryTreeNode root) {
    if (root == null) {
      return "";
    }

    // Root, iterate over each child
    StringBuilder str = new StringBuilder();
    serializeHelper(root, str);

    return str.toString();
  }

  private void serializeHelper(NAryTreeNode root, StringBuilder result) {
    if (root == null) {
      return;
    }

    result.append(root.val);
    result.append(',');
    if (root.children != null) {
      result.append(root.children.size());
    }
    else {
      result.append(0);
    }
    result.append(',');

    if (root.children != null) {
      for(NAryTreeNode child: root.children) {
        serializeHelper(child, result);
      }
    }
  }

  // Decodes your encoded data to tree.
  public NAryTreeNode deserialize(String data) {
   // split on , and then lets
    String split[] = data.split(",");

    if (split == null || split.length == 0) {
      return null;
    }

    return deserializeHelper(split, new int[]{0}, 0);

  }

  // here test variable is not needed - it is just to show that normal int variable
  // will have the value in recursive call as the previous value which was in the stack
  // hence need to use value in the array or else use linked list and remove values from linked list
  private NAryTreeNode deserializeHelper(String[] data, int[] pos, int test) {

    NAryTreeNode root = new NAryTreeNode();
    root.val = Integer.valueOf(data[pos[0]]);
    pos[0]++;
    test++;

    root.children = new LinkedList<>();
    int size = Integer.valueOf(data[pos[0]]);
    pos[0]++;
    test++;

    for (int i=0; i< size; i++) {
      root.children.add(deserializeHelper(data, pos, test));
    }

    return root;

  }

  public static void main(String args[]) {
    NAryTreeNode root = new NAryTreeNode();
    root.val = 1;

    NAryTreeNode child4 = new NAryTreeNode();
    child4.val = 5;

    NAryTreeNode child5 = new NAryTreeNode();
    child5.val = 6;


    NAryTreeNode child1 = new NAryTreeNode();
    child1.val = 3;
    NAryTreeNode arr[] = {child4, child5};
    child1.children = new LinkedList<>(Arrays.asList(arr));

    NAryTreeNode child2 = new NAryTreeNode();
    child2.val = 2;

    NAryTreeNode child3 = new NAryTreeNode();
    child3.val = 4;



    NAryTreeNode arr1[] = {child1, child2, child3};
    root.children = new LinkedList<>(Arrays.asList(arr1));

    SerializeDeserializeNTree obj = new SerializeDeserializeNTree();

    String serliazedString = obj.serialize(root);
    System.out.println(serliazedString);

    NAryTreeNode node = obj.deserialize(serliazedString);
    serliazedString = obj.serialize(node);
    System.out.println(serliazedString);
  }
}

//Solution that executed in Leet code
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
//class Codec {
//
//  // Encodes a tree to a single string.
//  public String serialize(Node root) {
//    // represent the tree as : 1 3 3 2 5 0 6 0 2 0 4 0
//    // node val, no. of children, child - preorder traversal
//
//    StringBuilder str = new StringBuilder();
//    return (serializeHelper(root, str)).toString();
//
//  }
//
//  private StringBuilder serializeHelper(Node root, StringBuilder result) {
//    if (root == null) {
//      return new StringBuilder();
//    }
//
//    result.append(root.val);
//    result.append(",");
//    result.append(root.children.size());
//    result.append(",");
//    for(Node child: root.children) {
//      result = serializeHelper(child, result);
//    }
//
//    return result;
//
//  }
//
//  // Decodes your encoded data to tree.
//  public Node deserialize(String data) {
//    if (data == null || data.length() == 0) {
//      return null;
//    }
//    String[] splitData = data.split(",");
//    return deserializeHelper(splitData, new int[]{0});
//  }
//
//  private Node deserializeHelper(String[] data, int [] pos) {
//    Node root = new Node();
//    root.val = Integer.valueOf(data[pos[0]]);
//    pos[0]++;
//    root.children = new LinkedList<Node>();
//    int size = Integer.valueOf(data[pos[0]]);
//    pos[0]++;
//
//    for (int i=0; i< size; i++) {
//      root.children.add(deserializeHelper(data, pos));
//    }
//
//    return root;
//  }
//}

