/**
 *               1
 *           2      3
 *        4    5   6  7
 *
 *        4 5 6 7 2 3 1
 *
 *
 * SOLUTION can use queue and add level order from right
 * Then just reversing the order will give you that
 */
package com.datastructure.binarytree;
import java.util.*;

public class BinaryTreeLevelOrderReverseTraversal {
  public static List<Integer> levelOrderReverse(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    List<Integer> list = new LinkedList<>();

    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      list.add(node.val);
      if (node.right!= null) queue.add(node.right);

      if (node.left != null) queue.add(node.left);
    }
    Collections.reverse(list);
    return list;
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

    System.out.println(levelOrderReverse(tree.root));
  }
}

