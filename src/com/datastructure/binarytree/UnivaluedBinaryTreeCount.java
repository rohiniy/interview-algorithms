/**
 * MEDIUM - GOOGLE, BOX
 *
 * Given a binary tree, count the number of uni-value subtrees.
 *
 * A Uni-value subtree means all nodes of the subtree have the same value.
 *
 * Example :
 *
 * Input:  root = [5,1,5,5,5,null,5]
 *
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 *
 * Output: 4
 */
package com.datastructure.binarytree;

public class UnivaluedBinaryTreeCount {
  public int countUnivalSubtrees(TreeNode root) {
    int count[] = new int[]{0};
    isUnivalSubtree(root, count);
    return count[0];
  }

  public boolean isUnivalSubtree(TreeNode root, int[] count) {
    if (root == null) {
      return true;
    }

    boolean isLeftUnival = isUnivalSubtree(root.left, count);
    boolean isRightUnival = isUnivalSubtree(root.right, count);

    if (root.left != null && root.left.val != root.val) {
      return false;
    }
    if (root.right != null && root.right.val != root.val) {
      return false;
    }
    if (!isLeftUnival || !isRightUnival) {
      return false;
    }

    // otherwise increment the count
    count[0]++;
    return true;
  }
}
