/**
 * LEFT View of a Binary Tree :
 *                    1
 *              2              3
 *                4         5         6
 *                       7
 *
 * [1 2 4 7] - i.e. left most child of every level
 *
 * Solution:
 * Use a map <Integer, Integer> [level_number, node_value]
 * Then for each level only store the 1st value
 *
 * You can also store form right to left in map and it will get overwritten
 */

package com.datastructure.binarytree;

import java.util.*;

public class BinaryTreePrintLeftView {
  public static void getLeftViewTree(TreeNode root) {
    Map<Integer, Integer> map = new HashMap<>();
    getLeftViewTreeHelper(root, 1, map);

    // map has the left view
    for (int i= 1; i<= map.size(); i++) {
      System.out.println(map.get(i));
    }
  }

  private static void getLeftViewTreeHelper(TreeNode root, int level, Map<Integer, Integer> map) {
    if (root == null) {
      return;
    }

    if (!map.containsKey(level)) {
      // if for a level value is not stored then only store it
      map.put(level, root.val);
    }

    getLeftViewTreeHelper(root.left, level+1, map);
    getLeftViewTreeHelper(root.right, level+1, map);

  }


  public static List<Integer> levelOrderLeftView(TreeNode root, Map<Integer, Integer> map) {
    List<Integer> result = new LinkedList<>();
    if (root == null) {
      return result;
    }

    int level = 0;
    // Queue
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      int size = queue.size();

      while (size > 0) {
        TreeNode node = queue.poll();

        map.put(level, node.val);
        if (node.right != null) {
          queue.add(node.right);
        }
        if (node.left != null) {
          queue.add(node.left);
        }
        size--;
      }

      level++;

    }

    for (int i=0; i<map.size(); i++) {
      result.add(map.get(i));
    }
    return result;
  }


  public static void main(String args[]) {
    BinaryTree tree = new BinaryTree();

    // Let us create a binary tree shown in above diagram
    tree.root = new TreeNode(1);
    tree.root.left = new TreeNode(2);
    tree.root.right = new TreeNode(3);
    tree.root.left.left = new TreeNode(4);
    tree.root.left.right = new TreeNode(5);
    tree.root.right.left = new TreeNode(6);
    tree.root.right.right = new TreeNode(7);
    tree.root.left.right.right = new TreeNode(10);


    /**
     *            1
     *          2   3
     *        4  5  6  7
     *            10
     */

    getLeftViewTree(tree.root);

    Map<Integer, Integer> map = new HashMap<>();

    System.out.println("Level Order Traversal::");
    System.out.println(BinaryTreeLevelOrderTraversal.levelOrder(tree.root));

    System.out.println(levelOrderLeftView(tree.root, map));
  }

}
