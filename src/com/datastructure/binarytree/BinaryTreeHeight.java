package com.datastructure.binarytree;

public class BinaryTreeHeight {
  public static int getHeightOfTree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int lHeight = getHeightOfTree(root.left);
    int rHeight = getHeightOfTree(root.right);

    return (Math.max(lHeight, rHeight) + 1);
  }

  public static void main(String args[]) {
    TreeNode root = new TreeNode(2);
    root.left = new TreeNode(1);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.left.right.left = new TreeNode(51);
    root.left.right.left.left = new TreeNode(5);

//    root.right.left = new TreeNode(51);
//    root.right.right = new TreeNode(52);

    System.out.println(getHeightOfTree(root));
  }
}
