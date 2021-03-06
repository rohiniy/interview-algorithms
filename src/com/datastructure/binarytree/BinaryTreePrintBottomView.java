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
        System.out.print(entry.getKey() + " ");
      }
    }
    System.out.println();

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

  private static int horizontalDistance(TreeNode root, int hLevel, Map<Integer, Integer> map,
                                        int smallestHLevel) {
    if (root == null) {
      return smallestHLevel;
    }
    // preOrder traversal
    map.put(hLevel, root .val); // 0 3 -1 8 -2 5
    smallestHLevel = smallestHLevel > hLevel ? hLevel : smallestHLevel; // 0 -1 -2
    smallestHLevel = horizontalDistance(root.left, hLevel -1, map, smallestHLevel);//5, -2, -1
    smallestHLevel = horizontalDistance(root.right, hLevel +1, map, smallestHLevel);

    return smallestHLevel;
  }

  public static void printBottomView1(TreeNode root) {
    // hlevel, root.val
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

  /**
   * Print the tree with same horizontal distance in one list
   * starting from hDist = smallest from root and then group the same hDist nodes together
   * @param root
   * @return
   */
  public static List<List<Integer>> verticalOrder(TreeNode root) {
    // left = -1, right = +1, root = 0
    // create a map with <horizontal level, Lis<TreeNode>>
    Map<Integer, List<Integer>> map = new HashMap<>();
    List<List<Integer>> result = new ArrayList<>();

    int smallestHLevel = verticalLevel(root, 0, map, 0);

    for (int i=smallestHLevel; i< (smallestHLevel+map.size()); i++) {
      result.add(map.get(i));
    }
    return result;

  }

  private static int verticalLevel(TreeNode root, int level,
                            Map<Integer, List<Integer>> map, int smallestHLevel) {
    if (root == null) {
      return smallestHLevel;
    }

    smallestHLevel = smallestHLevel > level ? level : smallestHLevel;
    List<Integer> list = map.getOrDefault(level, new LinkedList<Integer>());
    list.add(root.val);
    map.put(level, list);
    smallestHLevel =verticalLevel(root.left, level-1, map, smallestHLevel);
    smallestHLevel =verticalLevel(root.right, level+1,map, smallestHLevel);
    return smallestHLevel;
  }

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
    tree.root.right.left.right = new TreeNode(10);

    /**
     *         1
     *     2       3
     *   4    5|6    7
     *          /\
     *          9 10
     *
     */

    printBottomView(tree.root);

    printBottomView1(tree.root);

    System.out.println("Vertical Order::");
    System.out.println(verticalOrder(tree.root));
  }
}
