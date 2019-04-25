/**
 * Find the Deepest Node in a Binary Tree
 * Given a binary tree, find the deep­est node in it.
 *
 * Examples:
 *
 * Input : Root of below tree
 *             1
 *           /   \
 *          2      3
 *         / \    / \
 *        4   5  6   7
 *                    \
 *                     8
 * Output : 8
 *
 * Input : Root of below tree
 *             1
 *           /   \
 *          2      3
 *                /
 *               6
 * Output : 6
 *
 * SOLUTION:
 * 1. Calculate height of the tree
 * 2. Now need to get the node at this height so you can do a postorder traversal with level
 *
 *
 * USE Inorder traversal with level and maintain a maxlevel
 */
package com.datastructure.binarytree;

import java.util.Hashtable;

public class DeepestNodeBinaryTree {
  public int deepestNode(TreeNode root) {

    if (root == null) {
      return 0;
    }

    // calculate the height of the tree
    int height = heightOfTree(root);

    int res[] = {0};
    getDeepestNode(root, height, res);

    return res[0];
  }

  private int heightOfTree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int lh = heightOfTree(root.left);
    int rh = heightOfTree(root.right);

    return Math.max(lh, rh) + 1;
  }
  private void getDeepestNode(TreeNode root, int height, int[] res) {
    // preorder traversal
    if (height == 1) {
      res[0] = root.val;
    }

    getDeepestNode(root.left, height--, res);
    getDeepestNode(root.right, height--, res);
  }

  /**
   * DOING BY INORDER TRAVERSAL WITH LEVELS
   */

  public int deepestNode2(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int res[] = {0};
    int[] maxLevel = {0};
    deepestLevelNodeVal(root, res, maxLevel, 0);

    return res[0];
  }

  private void deepestLevelNodeVal(TreeNode root, int res[], int maxLevel[], int level) {
    if (root == null) {
      return;
    }

    // go to left
    deepestLevelNodeVal(root.left, res, maxLevel, level+1);

    // check for root node
    if (level > maxLevel[0]) {
      maxLevel[0] = level;
      res[0] = root.val;
    }

    // got to right
    deepestLevelNodeVal(root.left, res, maxLevel, level+1);
  }

}
