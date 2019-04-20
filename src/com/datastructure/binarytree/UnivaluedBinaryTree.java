/**
 * A binary tree is univalued if every node in the tree has the same value.
 *
 * Return true if and only if the given tree is univalued.
 *
 *
 *
 * Example 1:
 * Input: [1,1,1,1,1,null,1]
 * Output: true
 *
 * Input: [2,2,2,5,2]
 * Output: false
 *
 *
 * Note:
 *
 * The number of nodes in the given tree will be in the range [1, 100].
 * Each node's value will be an integer in the range [0, 99].
 */
package com.datastructure.binarytree;

public class UnivaluedBinaryTree {
  public boolean isUnivalTree(TreeNode root) {
    // just do any order traversal and check if the values are same
    // we can do postorder traversal

    return isUnivalTreePostOrder(root);
  }

  public boolean isUnivalTreePostOrder(TreeNode root) {
    if (root == null) {
      return true;
    }

    boolean isLeftUniVal = isUnivalTreePostOrder(root.left);
    boolean isRightUniVal = isUnivalTreePostOrder(root.right);

    // check if the value is same for root and left and right
    if (root.left != null && root.val != root.left.val) {
      return false;
    }

    // check if the value is same for root and left and right
    if (root.right != null && root.val != root.right.val) {
      return false;
    }
    if ( !isLeftUniVal || !isRightUniVal) {
      return false;
    }

    return true;
  }
}
