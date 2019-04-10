package com.basicAlgorithms.medium;

class TreeNode {
  TreeNode left;
  TreeNode right;
  int val;

  TreeNode(int val) {
    this.val = val;
  }
}

public class BinaryTreeLowestCommonAncestor {
  public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return null;
    }
    if (root == p || root == q) {
      return root;
    }

    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);

    if (left != null && right != null) {
      return root;
    }
    return left!=null ? left : right;
  }
  // Driver program to test above functions
  public static void main(String[] args) {

    // Let us create binary tree given in
    // the above example
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(7);
    root.right.left.right = new TreeNode(8);

    TreeNode node = lowestCommonAncestor(root, root.left.left, root.left.right);
    System.out.println("Common Ancestor(4, 5) = "
        + node.val);

    node = lowestCommonAncestor(root, root.left, root.left.left);
    System.out.println("Common Ancestor(2, 4) = "
        + node.val);
  }

}
