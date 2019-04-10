/**
 * Minimum distance between BST node values
 *
 * Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.
 *
 * Example :
 *
 * Input: root = [4,2,6,1,3,null,null]
 * Output: 1
 * Explanation:
 * Note that root is a TreeNode object, not an array.
 *
 * The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
 *
 *           4
 *         /   \
 *       2      6
 *      / \
 *     1   3
 *
 *
 *
 * while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
 * Note:
 * The size of the BST will be between 2 and 100.
 * The BST is always valid, each node's value is an integer, and each node's value is different.
 *
 *           90
 *         /   \
 *       69      null
 *      / \
 *    49   89
 *      \
 *      52
 *
 *  Here it would be 90-89 = 1
 *
 *
 *  SOLUTION:
 *  When you represent in Inorder traversal that is the ascending order
 *  49 52 69 89 90
 */
package com.basicAlgorithms.medium;

public class BinaryTreeSmallestDifferenceInKeys {
  int min = Integer.MAX_VALUE;
  TreeNode prev = null;
  public int minDiffInBST(TreeNode root) {
    inOrder(root);
    return min;

  }
  private void inOrder(TreeNode root) {
    if (root == null) {
      return;
    }

    inOrder(root.left);
    if (prev != null) {
      min = Math.min(min, root.val - prev.val);
    }
    prev = root;
    inOrder(root.right);

  }

  public static void main(String args[]) {
// Let us create binary tree given in
    // the above example
    TreeNode root = new TreeNode(90);
    root.left = new TreeNode(69);
    root.left.left = new TreeNode(49);
    root.left.right = new TreeNode(89);
    root.left.left.right = new TreeNode(52);

    BinaryTreeSmallestDifferenceInKeys obj = new BinaryTreeSmallestDifferenceInKeys();
    int min = obj.minDiffInBST(root);
    System.out.println(min);
  }
}
