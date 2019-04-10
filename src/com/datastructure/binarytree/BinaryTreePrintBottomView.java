/**
 * Given a Binary Tree, we need to print the bottom view from left to right.
 * A node x is there in output if x is the bottommost node at its horizontal distance.
 * Horizontal distance of left child of a node x is equal to horizontal distance of x minus 1, and
 * that of right child is horizontal distance of x plus 1.
 *
 * Examples:
 *
 *                       20
 *                     /    \
 *                   8       22
 *                 /   \      \
 *               5      3      25
 *                     / \
 *                   10    14
 *
 * For the above tree the output should be 5, 10, 3, 14, 25.
 *
 * If there are multiple bottom-most nodes for a horizontal distance from root,
 * then print the later one in level traversal. For example, in the below diagram,
 * 3 and 4 are both the bottom-most nodes at horizontal distance 0, we need to print 4.
 *                       20
 *                     /    \
 *                   8       22
 *                 /   \    /   \
 *               5      3 4     25
 *                     / \
 *                   10     14
 *              -2   -1  0  1    2  -- Horizontal Distances from root
 * For the above tree the output should be 5, 10, 4, 14, 25.
 *
 *
 * SOLUTION:
 * Map<Horizontal distance, <nodeVal, level>>
 * do a pre-order traversal and then a node at horizontal distance is 1st to be seen add it
 * if already present horizontal distance - then compare the level and if current node's
 * level is higher then replace the nodeVal and level
 */
package com.datastructure.binarytree;

import java.util.*;

public class BinaryTreePrintBottomView {
  public static void printBottomView(TreeNode root) {
    // Map<Horizontal distance, <nodeVal, level>>
    Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

    printBottomViewHelper(root, map, 0, 0, 0);

    int smallestHorizontalDistance = 0;

    for (Map.Entry entry: map.entrySet()) {
      if (smallestHorizontalDistance > (Integer)entry.getKey()) {
        smallestHorizontalDistance = (Integer)entry.getKey();
      }
    }

    for (int i = smallestHorizontalDistance; i < (smallestHorizontalDistance + map.size()); i++) {
      Map<Integer, Integer> m = map.get(i);
      for (Map.Entry entry: m.entrySet()) {
        System.out.println(entry.getKey());
      }
    }

  }
  private static void printBottomViewHelper(TreeNode root, Map<Integer,
      Map<Integer, Integer>> map, int horizontalDistance, int level, Integer smallestHorizontalDistance) {

    if (root == null) {
     return;
   }

    if (!map.containsKey(horizontalDistance)) {
      // then add in the map
      HashMap<Integer, Integer> internalMap = new HashMap<>();
      internalMap.put(root.val, level);
      map.put(horizontalDistance, internalMap);
      if (smallestHorizontalDistance > horizontalDistance) {
        smallestHorizontalDistance = horizontalDistance;
      }
    }
    else {
      // compare the level
      //Map<Horizontal distance, <nodeVal, level>>
       Map<Integer, Integer> internalMap = map.get(horizontalDistance);
       for(Map.Entry entry: internalMap.entrySet()) {
         if ((Integer)entry.getValue() < level) {
           // put this new node value and level
           HashMap<Integer, Integer> newMap = new HashMap<>();
           newMap.put(root.val, level);
           map.put(horizontalDistance, newMap);
         }
       }
    }
   // preorder: L, R, Root
    printBottomViewHelper(root.right, map, horizontalDistance +1, level+1, smallestHorizontalDistance);
    printBottomViewHelper(root.left, map, horizontalDistance-1, level+1, smallestHorizontalDistance);
  }


  /**
   *  *                       20
   *  *                     /    \
   *  *                   8       22
   *  *                 /   \    /   \
   *  *               5      3 4     25
   *  *                     / \
   *  *                   10     14
   *  *              -2   -1  0  1    2  -- Horizontal Distances from root
   */

  // <20 0> <8 -1> <22 -1> <>

  private static int horizontalDistance(TreeNode root, int hLevel, Map<Integer, Integer> map, int smallestHLevel) {
    if (root == null) {
      return smallestHLevel;
    }
    // preOrder traversal
    map.put(hLevel, root .val);
    smallestHLevel = smallestHLevel > hLevel ? hLevel : smallestHLevel;
    smallestHLevel = horizontalDistance(root.left, hLevel -1, map, smallestHLevel);
    smallestHLevel = horizontalDistance(root.right, hLevel +1, map, smallestHLevel);

    return smallestHLevel;
  }

  public static void printBottomView1(TreeNode root) {
    Map<Integer, Integer> map = new HashMap<>();
    int smallestHLevel = horizontalDistance(root, 0, map, 0);

    for (int i= smallestHLevel; i < (smallestHLevel + map.size()); i++) {
      System.out.print(map.get(i) + " , ");
    }
  }

  public static List<Integer> levelOrder(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    List<Integer> list = new LinkedList<>();
    int nLevel = 0;
    list.add(0);

    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();


      if (node.left != null) {
        list.add(nLevel - 1);
        queue.add(node.left);
      }

      if (node.right!= null) {
        list.add(nLevel + 1);
        queue.add(node.right);
      }

      //nLevel--;
    }
    return list;
  }

  // root = val = 0
  // left, val -1 = -1
  // right, val+1 = 1

  // Map <>

  public static void main(String args[]) {
    BinaryTree tree = new BinaryTree();

    // Let us create a binary tree shown in above diagram
    tree.root = new TreeNode(1);
    tree.root.left = new TreeNode(2);
    tree.root.right = new TreeNode(3);
    tree.root.left.left = new TreeNode(4);
    tree.root.left.right = new TreeNode(5);
    tree.root.right.left = new TreeNode(6);
    tree.root.right.right = new TreeNode(7);
    tree.root.right.left.left = new TreeNode(9);

    /**
     *         1
     *     2         3
     *   4   5    6    7
     *         10    9
     *
     */

    printBottomView(tree.root);

    printBottomView1(tree.root);
  }
}
