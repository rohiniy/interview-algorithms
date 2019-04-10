/**
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *
 *
 *
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
 *
 *
 * SOLUTION
 * 1. Basic approach:
 * A tree can have the diameter passing through root: lh + rh
 * But, a tree can have diameter not passing through root : hence max(leftDiameter, rDiameter, (lh+rh))
 *
 * This can be done by simple case
 *
 * binaryTreeDiameter(TreeNode root) {
 *   if (root == null) {
 *     return 0;
 *   }
 *
 *   int lh = height(root.left); // Complexity : for height = O(n)
 *   int rh = height(root.right);
 *
 *   int lDiameter = binaryTreeDiameter(node.left); // complexity for cal diameter
 *   int rDiameter = binaryTreeDiameter(node.right);
 *
 *   return Math.max((lh + rh), Math.max(lDiameter, rDiameter));
 * }
 *
 * height(TreeNode root){
 * if (root == null) return 0;
 *   int lh = height(root.left) + 1;
 *   int rh = height(root.right) + 1;
 *
 *   return Math,max(lh, rh);
 * }
 *
 * Complexity: O(n * height of tree)
 * For balanced tree: O(n * log n)
 * For skewed tree: O(n * n) = n2
 *
 * Hence, better approach: is to calculate height and diameter only once.
 * So, we can have return type as array[height, diameter]
 *  - Complexity: O(n)
 *
 */
package com.datastructure.binarytree;

public class BinaryTreeDiameter {
  public int diameterOfBinaryTree(TreeNode root) {
    int result[] = diameterAndHeight(root);
    return result[1];
  }

  private int[] diameterAndHeight(TreeNode root) {
    int [] result = {0, 0};
    if (root == null) {
      return result;
    }

    int lo[] = diameterAndHeight(root.left);
    int ro[] = diameterAndHeight(root.right);

    // cal height = Max(lHeight, rHeight);
    // cal diameter as Max(lheight+rHeight, lDiameter, rDiameter)
    int heightTree = Math.max(lo[0], ro[0]) + 1;

    // height of tree
    result[0] = heightTree;
    result[1] = Math.max((lo[0] + ro[0]), Math.max(lo[1], ro[1]));
    return result;
  }
}
