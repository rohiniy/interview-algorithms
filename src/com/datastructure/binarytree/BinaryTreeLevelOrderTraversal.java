package com.datastructure.binarytree;
import java.util.*;

public class BinaryTreeLevelOrderTraversal {
  public static List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new LinkedList<>();
    if (root == null) {
      return result;
    }

    // Queue
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> list = new LinkedList<>();

      int i = size;
      while (i > 0) {
        // pop the elements and add their children
        TreeNode node = queue.poll();
        // add this node to the list
        list.add(node.val);

        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
        i--;
      }
      result.add(list);

    }
    return result;
  }

  public static void main(String args[]) {
    BinaryTree tree = new BinaryTree();

    // Let us create a binary tree shown in above diagram
    tree.root = new TreeNode(1);
    tree.root.left = new TreeNode(2);
    tree.root.right = new TreeNode(2);
    tree.root.left.left = new TreeNode(3);
    tree.root.left.right = new TreeNode(4);
    tree.root.right.left = new TreeNode(4);
    tree.root.right.right = new TreeNode(3);

    System.out.println(levelOrder(tree.root));
  }
}
