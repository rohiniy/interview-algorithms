/**
 * Given a binary tree, find its maximum depth.
 *
 *     The maximum depth is the number of nodes along the longest path
 *     from the root node down to the farthest leaf node.
 *
 *     Note: A leaf is a node with no children.
 *
 *     Example:
 *
 *     Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *     / \
 *     9  20
 *        /  \
 *       15   7
 *     return its depth = 3.
 *
 *
 * -----------------------------------Problem 2: Is tree symmetric
 * Check if tree is symmetric around the center
 *
 *Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
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
 *
 *
 * ---------------MEDIUM--------------Problem 3: Find diameter of the tree (longest path)------
 * Solution:
 * Via Dynamic Programming
 * In each branch check if left is large or right is large
 *
 *
 *
 * ---------------Problem 4: Print level wise tree ------------------
 *
 * Solution:
 * Use queue to store the nodes
 *
 * ------------------PROBLEM -Sum of Left Leaves--ASKED in COMPANIES----
 *
 * Find the sum of all left leaves in a given binary tree.
 *
 * Example:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *   15   7
 *
 *
 * There are two left leaves in the binary tree, with values 9 and 15 respectively.
 * Return 24.
 *
 * -------------------PROBLEM- Inorder Traversal Iteratively - ASKED IN COMPANIES
 *  Left Root Right
 *
 *
 *
 *Using stack
 * 1. curr = root
 * 2. Run in loop if stack is not empty or curr != null
 * 3. Go to left till end. Push all nodes in stack
 * 4. Once left is over. Pop the element
 * 5. Print element
 * 6. go to right: cur = curr.right
 *
 * --------------------PROBLEM - Binary Tree Zigzag Level Order Traversal
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * return its zigzag level order traversal as:
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 *
 *
 */

package com.datastructure.binarytree;


import java.util.*;

public class BinaryTree {

  TreeNode root;

  public void deleteTree(TreeNode root) {
    root = null;
  }


  /**
   * Cal Max Depth Recursive way
   * @param root
   * @return
   */
  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
  }

  /**
   * Iterative way will be doing level order traversal
   */

  public int maxDepthITerative(TreeNode root) {
    // get a queue and store the nodes level wise

    Queue<TreeNode> queue = new LinkedList<>();
    if (root == null) {
      return 0;
    }

    queue.add(root);
    int height = 0;

    while(true) {
      int nodeCount = queue.size();
      if (nodeCount == 0) {
        return height;
      }
      height++;

      // Dequeue all nodes of current level and Enqueue all
      // nodes of next level
      while (nodeCount > 0) {
        TreeNode node = queue.poll();
        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right !=null) {
          queue.add(node.right);
        }
        nodeCount--;
      }
    }
  }


  /**
   * Check if tree is symmetric around the center
   *
   * @param root
   * @return
   */
  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    if (root.left == null && root.right == null) {
      return true;
    }
    return isSymmetricHelper(root.left, root.right);
  }

  public boolean isSymmetricHelper (TreeNode leftNode, TreeNode rightNode) {

    if (leftNode == null ||  rightNode == null) {
      return leftNode == rightNode;
    }

    return leftNode.val == rightNode.val &&
        isSymmetricHelper(leftNode.left, rightNode.right) &&
        isSymmetricHelper(leftNode.right, rightNode.left);
  }

  /**
   * Get the diameter of the tree
   * This will be the longest path in the tree which can be one not through the root
   * Need to compare the length of left and right subtree and return only the largest length branch
   *
   * @param root
   * @return
   */
  public int getDiameterOfTree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int diameter = 0;
    int [] result = getDiameterOfTreeHelper(root);
    return result[1];
  }

  /**
   *
   * @param root
   */
  private int[] getDiameterOfTreeHelper(TreeNode root) {
    int [] result = {0, 0};
    // base case
    if (root == null) {
      return result;
    }

    int leftDiameterAndHeight[] = getDiameterOfTreeHelper(root.left);
    int rightDiameterAndHeight[] = getDiameterOfTreeHelper(root.right);

    // return [height, diameter]
    int heightTree = Math.max(leftDiameterAndHeight[0], rightDiameterAndHeight[0]) + 1;
    result[0] = heightTree;
    // diameter is max(leftHeight + rightHeight, lDiameter, rDiameter)
    result[1] = Math.max((leftDiameterAndHeight[0] +rightDiameterAndHeight[0]),
        Math.max(leftDiameterAndHeight[1], rightDiameterAndHeight[1]));
    return result;
  }


  public void printLevelOrder(TreeNode root) {
    if (root == null) {
      return;
    }
    Queue<TreeNode> q = new LinkedList<>();

    q.add(root);
    System.out.println("[");
    while (!q.isEmpty()) {
      int count = q.size();
      System.out.print("[");
      while (count > 0) {
        TreeNode node = q.poll();
        if (count == 1) {
          System.out.print("" + node.val +"");
        }
        else {
          System.out.print("" + node.val +",");
        }

        if (node.left != null) {
          q.add(node.left);
        }
        if (node.right != null) {
          q.add(node.right);
        }
        count--;
      }
      System.out.print("] \n");
    }
    System.out.println("]");
  }


  public List<List<Integer>> levelOrderList(TreeNode root) {
    if (root == null) {
      return null;
    }
    Queue<TreeNode> q = new LinkedList<>();
    List<List<Integer>> list = new LinkedList<List<Integer>>();
    q.add(root);

    while (!q.isEmpty()) {
      int count = q.size();
      LinkedList<Integer> internalList = new LinkedList<>();

      while(count > 0) {
        TreeNode node = q.poll();
        internalList.add(node.val);

        if (node.left!= null) {
          q.add(node.left);
        }
        if (node.right != null) {
          q.add(node.right);
        }

        count--;
      }
      list.add(internalList);
    }

    return list;
  }

  public int sumOfLeftLeaves(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return sumOfLeftLeavesHelper(root, false, 0);
  }

  private int sumOfLeftLeavesHelper(TreeNode root, boolean isLeft, int sum) {
    if (root == null) {
      return 0;
    }
    if (isLeft && root.left == null && root.right == null) {
      // left leaf node
      return root.val;
    }
    else if (!isLeft && root.left == null && root.right == null) {
      // right leaf node - ignore
      return 0;
    }

    return sum + sumOfLeftLeavesHelper(root.left, true, sum)
        + sumOfLeftLeavesHelper(root.right, false, sum);
  }

  /**
   * Function to give list of nodes in inorder traversal iteratively
   * @param root
   * @return
   */
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    if (root == null) {
      return list;
    }

    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode curr = root;
    while (curr!= null || !stack.isEmpty()) {
      // push all left nodes inside stack
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }

      TreeNode node = stack.pop();
      curr = node;
      list.add(node.val);
      curr = curr.right;
    }

    return list;
  }

  /**
   * Given a binary tree, return the zigzag level order traversal of its nodes' values.
   * (ie, from left to right, then right to left for the next level and alternate between).
   * @param root
   * @return
   */
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    if (root == null) {
      return result;
    }

    int level =1;
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    q.add(root);
    while (!q.isEmpty()) {
      int size = q.size();
      List<Integer> list = new ArrayList<Integer>();
      while (size > 0) {
        TreeNode node = q.poll();
        list.add(node.val);
        if (node.left != null) {
          q.add(node.left);
        }
        if (node.right != null) {
          q.add(node.right);
        }

        size--;
      }
      if (level %2 == 0) {
        Collections.reverse(list);
      }
      result.add(list);
      level++;
    }


    return result;
  }

  public List<List<Integer>> zigzagLevelOrderPrecise(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if(root==null){
      return result;
    }
    int level = 0;
    boolean order = true;
    findSublist(root, result, level, order);
    return result;

  }

  private void findSublist(TreeNode root, List<List<Integer>> result, int level, boolean order){
    if(root==null){
      return;
    }
    List<Integer> sublist;
    if(level < result.size()){
      sublist = result.get(level);
    } else {
      sublist = new ArrayList<>();
      result.add(sublist);
      sublist = result.get(level);
    }
    if(order){
      sublist.add(root.val);
    }else{
      sublist.add(0, root.val);
    }
    int temp = level + 1;
    findSublist(root.left, result, temp , !order);
    findSublist(root.right, result, temp , !order);
  }

  /**
   *          1
   *     2        3
   *   4  5   6
   * @param root
   * @return
   */
  public int sumOfLeftNodes(TreeNode root) {
    if (root == null) return 0;
    return sumOfLeftNodesHelper(root, 0, false);
  }


  private static int sumOfLeftNodesHelper(TreeNode root, int sum, boolean isLeft) {
    if (root == null) {
      return sum;
    }

    sum = (root.left == null && root.right == null && isLeft) ? root.val + sum : sum;

    sum = sumOfLeftNodesHelper(root.left, sum, true);
    sum = sumOfLeftNodesHelper(root.right, sum, false);
    return sum;
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

    System.out.println("Height of tree iterative is " + tree.maxDepthITerative(tree.root));
    System.out.println("Height of tree recursive is " + tree.maxDepth(tree.root));
    System.out.println("The tree is Symmetric::" + tree.isSymmetric(tree.root));
    System.out.println("Diameter of the tree::" + tree.getDiameterOfTree(tree.root));
    tree.printLevelOrder(tree.root);

    System.out.println("Getting list for level order::");
   List<List<Integer>> list = tree.levelOrderList(tree.root);

   System.out.println(list);
//    for (List internalList: list) {
//      for (Object val: internalList) {
//        System.out.print(val+", ");
//      }
//      System.out.println();
//    }

    System.out.println("Inorder tree traversal::");
    BinaryTree tree1 = new BinaryTree();
    // Let us create a binary tree shown in above diagram
    tree1.root = new TreeNode(1);
    tree1.root.left = new TreeNode(2);
    tree1.root.right = new TreeNode(3);
    tree1.root.left.left = new TreeNode(4);
    tree1.root.left.right = new TreeNode(5);
    tree1.root.right.left = new TreeNode(6);

    System.out.println(tree1.inorderTraversal(tree1.root));

    System.out.println("Level order::");
    System.out.println(tree1.levelOrderList(tree1.root));

    System.out.println("Zig zag level order traversal::");
    System.out.println(tree1.zigzagLevelOrderPrecise(tree1.root));

    System.out.println("Left nodes summation = "+ tree1.sumOfLeftNodes(tree1.root));



    tree1.deleteTree(tree1.root);
    tree1.root = null;

  }
}
