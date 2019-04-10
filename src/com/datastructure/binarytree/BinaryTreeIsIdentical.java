package com.datastructure.binarytree;

public class BinaryTreeIsIdentical {
  public static boolean isIdentical(TreeNode root1, TreeNode root2) {
    if (root1 == null && root2 == null) {
      return true;
    }
    if (root1 == null || root2 == null) {
      return false;
    }

    return (root1.val == root2.val)
        && isIdentical(root1.left, root2.left)
        && isIdentical(root1.right, root2.right);
  }

  public static void main(String args[]) {
    TreeNode root1 = new TreeNode(1);
    TreeNode root2 = new TreeNode(1);
    root1.left = new TreeNode(2);
    root2.left = new TreeNode(2);
    root1.right = new TreeNode(3);
    root2.right = new TreeNode(3);
    root1.left.left = new TreeNode(4);


    System.out.println(isIdentical(root1, root2));
  }
}
