/**
 * Check if s is a subtree of T
 *Given two non-empty binary trees s and t, check whether tree t has exactly the
 * same structure and node values with a subtree of s. A subtree of s is a tree consists of a
 * node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
 *
 * Example 1:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return true, because t has the same structure and node values with a subtree of s.
 * Example 2:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return false
 *
 */
package com.datastructure.binarytree;

public class BinaryTreeSubtree {
  public static boolean isSubtree(TreeNode sRoot, TreeNode tRoot) {
    if (sRoot == null && tRoot == null) {
      return true;
    }

    if (sRoot == null || tRoot == null) {
      return false;
    }

    if (sRoot.val == tRoot.val && isIdentical(sRoot, tRoot)) {
      // check if they are identical
      return true;
    }

    return isSubtree(sRoot.left, tRoot) || isSubtree(sRoot.right, tRoot);
  }

  private static boolean isIdentical(TreeNode sRoot, TreeNode tRoot) {
    if (sRoot == null && tRoot == null) {
      return true;
    }

    if (tRoot == null || sRoot == null) {
      return false;
    }

    return sRoot.val == tRoot.val
        && isIdentical(sRoot.left, tRoot.left)
        && isIdentical(sRoot.right, tRoot.right);
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

    // Let us create a binary tree shown in above diagram
    BinaryTree tree1 = new BinaryTree();
    tree1.root = new TreeNode(1);
    tree1.root.left = new TreeNode(2);
    tree1.root.right = new TreeNode(3);
//    tree.root.left.left = new TreeNode(3);
//    tree.root.left.right = new TreeNode(4);
//    tree.root.right.left = new TreeNode(4);
//    tree.root.right.right = new TreeNode(3);

    System.out.println(isSubtree(tree.root, tree1.root));
  }
}
