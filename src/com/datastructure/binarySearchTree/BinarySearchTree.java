/**
 * ----------------Problem 1
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Example 1:
 *
 * Input:
 *     2
 *    / \
 *   1   3
 * Output: true
 * Example 2:
 *
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * Output: false
 * Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
 *              is 5 but its right child's value is 4.
 *
 *
 * SOLUTION:
 * We need to check if the node.left value is smaller than node.val
 * and node.right value > node.val
 * Recursively : At the start minValue = Long.MIN_VALUE, maxValue = Long.MAX_VALUE
 * return isValid(node.left, minValue, node.val) &&
 *        isValid(node.right, node.val, maxValue) &&
 *        node.val > minValue && node.val < maxValue
 *
 *----------------------------Problem 2 Symmetric Tree -------------------------------------------------
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * But the following [1,2,2,null,3,null,3] is not:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 *
 * Solution:
 * 1. Recursive solution where we focus on whether left.val == right.val ==null
 * 2. Need a helper so that we can pass (lChild, rChild)
 * 3. In this helper method we can compare if any one of it is null then they should be equal id not then return false
 * if (lChild == null || rChild == null) {
 *   return lChild == rChild;
 * } // basically both should be null
 *
 * 4. Else they are not null the they should be equal and then we recursively go to check left and right subtree
 * return lChild.val == rChild.val &&
 *      isSymmetricHelper(lChild.left, rChild.right) &&
 *      isSymmetricHelper(lChild.right, rChild.left)
 *
 *
 *
 * --------------------------------Problem 3 build a Balanced BST from sorted array
 *
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted array: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * SOLUTION:
 * 1. When the array is sorted then we can get the mid point and that will be the root
 * 2. Then left of the midpoint will be left subtree and right will be right subtree
 *
 *
 *
 *
 */

package com.datastructure.binarySearchTree;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree {
  private TreeNode root;

  public TreeNode search(int data) {
    return root.search(data);
  }

  public void insert(int data) {
    this.root = insertRec(data, this.root);
  }

  public TreeNode insertRec(int data, TreeNode root) {
    if (root == null) {
      root = new TreeNode(data);
      return root;
    }

    if (data > root.getData()) {
      root.setLeftChild(insertRec(data, root.getRightChild()));
    }

    if (data < root.getData()) {
      root.setRightChild(insertRec(data, root.getLeftChild()));
    }

    return root;
  }

  /**
   * Print the tree in level order - BFS for tree
   * @param root
   * @param level
   */
  public void printLevelOrderTraversal(TreeNode root, int level) {
    Queue<TreeNode> q = new LinkedList<>();

    if (root == null) {
      return;
    }
    q.add(root);

    while (!q.isEmpty()) {
      int count = q.size();
      System.out.print("\nLevel " + level++);

      while (count > 0) {
        TreeNode node = q.poll();
        System.out.println(node.getData());
        if (node.getLeftChild() != null) {
          q.add(node.getLeftChild());
        }
        if (node.getRightChild() != null) {
          q.add(node.getRightChild());
        }
        count--;
      }
    }
  }

  public void printInorderTraversal(TreeNode root) {

  }

  public void printPreorderTraversal(TreeNode root) {

  }

  public void printPostorderTraversal(TreeNode root) {

  }

  /**
   * A function which can be used to solve 3 questions
   * 1. Print Inorder traversal of tree
   * 2. Find kth smallest element
   * @param root
   */
  public void inOrderIterative(TreeNode root) {
    Stack<TreeNode> stack = new Stack();

    while (root != null || !stack.isEmpty()) {
      // add root in stack then add all left children
      while(root != null) {
        stack.push(root);
        root = root.getLeftChild();
      }

      // once all left nodes are added then go to right child
      root = stack.pop();
      // either print or add to list and return list
      System.out.println(" " + root.getData() + " ");
      root = root.getRightChild();
    }
  }

  /**
   * We have to check if the left node is between Integer.minValue and node.val
   * node.right is between node.val and Integer.maxVal
   *
   * @param root
   */
  public boolean isValidBST(TreeNode root) {
    // Need to pass Long.MAX_VALUE as node.val can be Integer.MAX_VAL value too
    // but cannot say node.val >= minValue as the values in tree are strictly less or greater
    // hence pass Long.MIN_VALUE and Long.MAX_VALUE
    return isValidNode(root, Long.MAX_VALUE, Long.MIN_VALUE);
  }

  public boolean isValidNode(TreeNode node, long maxVal, long minValue) {
    if (node == null) {
      return true;
    }
    return  node.getData() < maxVal && node.getData() > minValue
        &&
        isValidNode(node.getLeftChild(), node.getData(), minValue)
        &&
        isValidNode(node.getRightChild(), maxVal, node.getData());
  }

  public TreeNode sortedArrayToBST(int[] nums) {
    int n = nums.length;
    int midpoint = (n-1)/2;

    TreeNode root = new TreeNode(nums[midpoint]);

    root.setLeftChild( sortedArrayToBSTHelper(nums, 0, midpoint-1));
    root.setRightChild( sortedArrayToBSTHelper(nums, midpoint+1, n-1));

    return root;
  }

  private TreeNode sortedArrayToBSTHelper(int[] nums, int start, int end) {
    if (start < end) {
      return null;
    }
    int midpoint = (start + end)/2;

    TreeNode node = new TreeNode(nums[midpoint]);
    node.setLeftChild(sortedArrayToBSTHelper(nums, start, midpoint -1));
    node.setRightChild(sortedArrayToBSTHelper(nums, midpoint + 1,  end));

    return node;
  }


  public static void main(String args[]) {
    BinarySearchTree tree = new BinarySearchTree();
    tree.insert(1);
    tree.insert(2);
    tree.insert(2);
    tree.insert(3);
    tree.insert(4);
    tree.insert(4);
    tree.insert(3);

    BinarySearchTree treeFromArray = new BinarySearchTree();
    int nums[] = {-10,-3,0,5,9};
    treeFromArray.root = treeFromArray.sortedArrayToBST(nums);

    treeFromArray.printLevelOrderTraversal(treeFromArray.root, 0);
  }
}
