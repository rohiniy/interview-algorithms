/**
 * Serialization is the process of converting a data structure or object into a sequence of
 * bits so that it
 * can be stored in a file or memory buffer, or transmitted across a network connection link to be
 * reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree
 * can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Example:
 *
 * You may serialize the following tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * as "[1,2,3,null,null,4,5]"
 *
 * Clarification: The above format is the same as how LeetCode serializes a binary tree.
 * You do not necessarily need to follow this format, so please be creative and come up with different
 * approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize
 * algorithms should be stateless.
 *
 *
 * SOLUTION:
 * 1. Preorder traversal with representation as root = i, left = 2i + 1, right = 2i + 2
 * 2. Add 'null' for no children
 * Representation for above tree: 1 2 null null 3 4 5
 */
package com.datastructure.binarytree;

public class SerializeDeserializeBinaryTree {
  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder str = new StringBuilder();
    serializeHelper(root, str);
    return str.toString();
  }

  private void serializeHelper(TreeNode root, StringBuilder str) {
    if (root == null) {
      str.append("null,");
    }
    else {
      // recursive preorder call :  Root, left, right
      str.append(root.val);
      str.append(",");
      serializeHelper(root.left, str);
      serializeHelper(root.right, str);
    }
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    String[] splitArr = data.split(",");
    TreeNode head = null;

    if (splitArr[0].equals("null,")) {
      // root is null
      return head;
    }

    head = deserializeHelper(splitArr, new int[]{0});

    return head;
  }

  private TreeNode deserializeHelper(String[] splitArr, int pos[]) {
    if (splitArr == null || splitArr.length == 0) {
      return null;
    }

    TreeNode root;
    if (splitArr[pos[0]].equals("null")) {
      pos[0]++;
      return null;
    }
    else {
      root = new TreeNode(Integer.valueOf(splitArr[pos[0]]));
      pos[0]++;
      root.left = deserializeHelper(splitArr, pos);
      root.right = deserializeHelper(splitArr, pos);
    }

    return root;
  }

  public static void main(String args[]) {
    BinaryTree tree = new BinaryTree();

    // Let us create a binary tree shown in above diagram
    tree.root = new TreeNode(1);
    tree.root.left = new TreeNode(2);
    tree.root.right = new TreeNode(3);
    tree.root.right.left = new TreeNode(4);
    tree.root.right.right = new TreeNode(5);

    /*
                  1
                2    3
                    4   5
     */

    SerializeDeserializeBinaryTree obj = new SerializeDeserializeBinaryTree();
    String serializedTree = obj.serialize(tree.root);
    System.out.println(serializedTree);

    TreeNode root = obj.deserialize(serializedTree);
    tree.printLevelOrder(root);
  }
}
